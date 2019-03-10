package us.marseilles.fitocracy.client.dao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.google.common.reflect.TypeToken;
import lombok.SneakyThrows;
import us.marseilles.fitocracy.client.model.v2.ResponseWrapper;
import us.marseilles.fitocracy.model.v1.ActivityStub;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;
import us.marseilles.fitocracy.model.v2.description.ExerciseDescription;
import us.marseilles.fitocracy.model.v2.user.User;
import us.marseilles.fitocracy.model.v2.workout.Workout;

public class FitoActivityDaoImpl implements FitoActivityDao
{
    /* URL constants */

    private static final String HOST = "https://www.fitocracy.com/";

    private static final String USER_ACTIVITIES_URL_TEMPLATE = HOST + "get_user_activities/%s/";

    private static final String ACTIVITY_URL_TEMPLATE = HOST + "_get_activity_history_json/?activity-id=%d";

    private static final String V2_API_URL = HOST + "api/v2/";

    private static final String V2_EXERCISES_URL = V2_API_URL + "exercises/";

    private static final String V2_USER_URL_TEMPLATE = V2_API_URL + "user/%s/";

    private static final String V2_WORKOUT_URL_TEMPLATE = V2_USER_URL_TEMPLATE + "workouts/%s/?timezone_offset=%f";

    /* Header constants */

    private static final String FITO_USER_ID_HEADER = "X-Fitocracy-User";

    /* Marshalling types */

    private static final Type V1_ACTIVITY_STUBS_TYPE = new TypeToken<List<ActivityStub>>() {}.getType();

    private static final Type V1_ACTIVITY_WORKOUT_TYPE = new TypeToken<List<ActivityWorkout>>() {}.getType();

    private static final Type V2_EXERCISES_TYPE =
        new TypeToken<ResponseWrapper<List<ExerciseDescription>>>() {}.getType();

    private static final Type V2_USER_TYPE = new TypeToken<ResponseWrapper<User>>() {}.getType();

    private static final Type V2_WORKOUT_TYPE = new TypeToken<ResponseWrapper<List<Workout>>>() {}.getType();

    /**
     * Shared, thread-safe JSON factory
     */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    /* Instance members */

    private final HttpRequestFactory requestFactory;


    public FitoActivityDaoImpl()
    {
        requestFactory = new NetHttpTransport().createRequestFactory(
            request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));
    }
    
    @Override
    @SneakyThrows(IOException.class)
    public String getOwnUserId(String sessionId)
    {
        // Fast-returning call which only requires a sessionId; returns HTTP 200 and null content for activity "-1"
        GenericUrl url = new GenericUrl(String.format(ACTIVITY_URL_TEMPLATE, -1));
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
    @SneakyThrows(IOException.class)
    public List<ActivityStub> getAllActivitiesForUser(String userId, String sessionId)
    {
        GenericUrl url = new GenericUrl(String.format(USER_ACTIVITIES_URL_TEMPLATE, userId));
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);
        
        HttpResponse httpResponse = httpRequest.execute();
        return (List<ActivityStub>) httpResponse.parseAs(V1_ACTIVITY_STUBS_TYPE);
    }

    @Override
    @SneakyThrows(IOException.class)
    public List<ActivityWorkout> getActivityForUser(long activityId, String sessionId)
    {
        GenericUrl url = new GenericUrl(String.format(ACTIVITY_URL_TEMPLATE, activityId));
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);
        
        HttpResponse httpResponse = httpRequest.execute();
        return (List<ActivityWorkout>) httpResponse.parseAs(V1_ACTIVITY_WORKOUT_TYPE);
    }

    @Override
    @SneakyThrows(IOException.class)
    public List<ExerciseDescription> getAllExerciseDescriptions(String sessionId)
    {
        GenericUrl url = new GenericUrl(V2_EXERCISES_URL);
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);

        HttpResponse httpResponse = httpRequest.execute();
        return ((ResponseWrapper<List<ExerciseDescription>>) httpResponse.parseAs(V2_EXERCISES_TYPE)).getData();
    }

    @Override
    @SneakyThrows(IOException.class)
    public User getUser(String userId, String sessionId)
    {
        GenericUrl url = new GenericUrl(String.format(V2_USER_URL_TEMPLATE, userId));
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);
        
        HttpResponse httpResponse = httpRequest.execute();
        return ((ResponseWrapper<User>) httpResponse.parseAs(V2_USER_TYPE)).getData();
    }
    
    @Override
    @SneakyThrows(IOException.class)
    public List<Workout> getWorkoutForUser(String userId, Date date, double tzOffset, String sessionId)
    {
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        GenericUrl url = new GenericUrl(String.format(V2_WORKOUT_URL_TEMPLATE, userId, formattedDate, tzOffset));
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, sessionId);

        HttpResponse httpResponse = httpRequest.execute();
        return ((ResponseWrapper<List<Workout>>) httpResponse.parseAs(V2_WORKOUT_TYPE)).getData();
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