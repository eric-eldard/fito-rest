package us.marseilles.fitocracy.rest.dao;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import us.marseilles.fitocracy.rest.domain.ActivityWorkout;
import us.marseilles.fitocracy.rest.domain.ActivityHistory;
import us.marseilles.fitocracy.rest.domain.ActivityStub;
import us.marseilles.fitocracy.rest.domain.ActivityStubs;

public class FitoActivityDaoImpl implements FitoActivityDao
{
    private static final String HOST = "https://www.fitocracy.com";
    
    private static final String PERFORMANCE_URL_TEMPLATE = HOST + "/profile/%s/?performance";
    
    private static final String USER_ACTIVITIES_URL_TEMPLATE = HOST + "/get_user_activities/%s/";

    private static final String ACTIVITY_URL_TEMPLATE = HOST + "/_get_activity_history_json/?activity-id=%d";
    
    private static final String FITO_USER_ID_HEADER = "X-Fitocracy-User";
    
    private final HttpRequestFactory requestFactory;

    private JsonFactory jsonFactory;

    public FitoActivityDaoImpl()
    {
        jsonFactory = new JacksonFactory();
        requestFactory = new NetHttpTransport().createRequestFactory(
            request -> request.setParser(new JsonObjectParser(jsonFactory)));
    }
    
    @Override
    public String getUserId(String username, String sessionId) throws IOException
    {
        GenericUrl url = new GenericUrl(String.format(PERFORMANCE_URL_TEMPLATE, username));
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);
        
        HttpResponse httpResponse = httpRequest.execute();
        List<String> headerValues = (List<String>) httpResponse.getHeaders().get(FITO_USER_ID_HEADER);
        
        if (headerValues == null || headerValues.isEmpty())
        {
            throw new IllegalStateException("No " + FITO_USER_ID_HEADER + 
                " header found; you're probably not logged in.");
        }
        
        return headerValues.get(0);
    }
    
    @Override
    public List<ActivityStub> getAllActivitiesForUser(String userId, String sessionId) throws IOException
    {
        GenericUrl url = new GenericUrl(String.format(USER_ACTIVITIES_URL_TEMPLATE, userId));
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);
        
        HttpResponse httpResponse = httpRequest.execute();
        return httpResponse.parseAs(ActivityStubs.class);
    }

    @Override
    public List<ActivityWorkout> getActivityForUser(int activityId, String sessionId) throws IOException
    {
        GenericUrl url = new GenericUrl(String.format(ACTIVITY_URL_TEMPLATE, activityId));
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);
        
        HttpResponse httpResponse = httpRequest.execute();
        return httpResponse.parseAs(ActivityHistory.class);
    }
    
    private HttpRequest createAuthenticatedGetRequest(GenericUrl url, String sessionId) throws IOException
    {
        HttpRequest httpRequest = requestFactory.buildGetRequest(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setCookie("sessionid=" + sessionId);
        httpRequest.setHeaders(headers);
        return httpRequest;
    }
}