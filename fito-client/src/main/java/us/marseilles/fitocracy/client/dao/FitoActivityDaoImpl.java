package us.marseilles.fitocracy.client.dao;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.common.reflect.TypeToken;
import lombok.SneakyThrows;
import us.marseilles.fitocracy.client.model.v2.ResponseWrapper;
import us.marseilles.fitocracy.client.util.HttpUtils;
import us.marseilles.fitocracy.model.identity.AuthenticatedUser;
import us.marseilles.fitocracy.model.v1.ActivityStub;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;
import us.marseilles.fitocracy.model.v2.description.ExerciseDescription;
import us.marseilles.fitocracy.model.v2.user.User;
import us.marseilles.fitocracy.model.v2.workout.Workout;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /* Request constants */

    private static final String CSRFTOKEN_COOKIE_NAME = "csrftoken";

    private static final String SESSIONID_COOKIE_NAME = "sessionid";

    private static final String FITO_USER_ID_HEADER = "X-Fitocracy-User";

    private static final String LOGIN_REQUEST_BODY =
        "username=%s&password=%s&csrfmiddlewaretoken=%s&is_username=1&json=1";

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
    private static final JsonFactory JSON_FACTORY = new GsonFactory();

    /* Instance members */

    private final HttpRequestFactory requestFactory;


    public FitoActivityDaoImpl()
    {
        requestFactory = new NetHttpTransport().createRequestFactory(
            request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));
    }

    @Override
    @SneakyThrows(IOException.class)
    public AuthenticatedUser authenticate(String username, String password)
    {
        String csrfToken = getCsrfToken();

        String content = String.format(LOGIN_REQUEST_BODY, username, password, csrfToken);
        byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
        HttpContent httpContent = new ByteArrayContent("application/x-www-form-urlencoded", byteContent);

        HttpRequest httpRequest = createRequest(HttpMethods.POST, HOST + "accounts/login", httpContent);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept("*/*");
        requestHeaders.setContentType("application/x-www-form-urlencoded");
        requestHeaders.setCookie(CSRFTOKEN_COOKIE_NAME + '=' + csrfToken);
        requestHeaders.set("Referer", HOST);
        httpRequest.setHeaders(requestHeaders);

        HttpResponse httpResponse = httpRequest.execute();
        String userId = HttpUtils.getResponseHeader(httpResponse, FITO_USER_ID_HEADER);
        String sessionId = HttpUtils.getResponseCookie(httpResponse, SESSIONID_COOKIE_NAME);

        return new AuthenticatedUser(username, userId, sessionId, csrfToken);
    }
    
    @Override
    @SneakyThrows(IOException.class)
    public List<ActivityStub> getAllActivitiesForUser(AuthenticatedUser authenticatedUser)
    {
        String url = String.format(USER_ACTIVITIES_URL_TEMPLATE, authenticatedUser.getUserId());
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, authenticatedUser);
        
        HttpResponse httpResponse = httpRequest.execute();
        return (List<ActivityStub>) httpResponse.parseAs(V1_ACTIVITY_STUBS_TYPE);
    }

    @Override
    @SneakyThrows(IOException.class)
    public List<ActivityWorkout> getActivityForUser(long activityId, AuthenticatedUser authenticatedUser)
    {
        String url = String.format(ACTIVITY_URL_TEMPLATE, activityId);
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, authenticatedUser);
        
        HttpResponse httpResponse = httpRequest.execute();
        return (List<ActivityWorkout>) httpResponse.parseAs(V1_ACTIVITY_WORKOUT_TYPE);
    }

    @Override
    @SneakyThrows(IOException.class)
    public List<ExerciseDescription> getAllExerciseDescriptions(AuthenticatedUser authenticatedUser)
    {
        HttpRequest httpRequest = createAuthenticatedGetRequest(V2_EXERCISES_URL, authenticatedUser);

        HttpResponse httpResponse = httpRequest.execute();
        return ((ResponseWrapper<List<ExerciseDescription>>) httpResponse.parseAs(V2_EXERCISES_TYPE)).getData();
    }

    @Override
    @SneakyThrows(IOException.class)
    public User getUser(AuthenticatedUser authenticatedUser)
    {
        String url = String.format(V2_USER_URL_TEMPLATE, authenticatedUser.getUserId());
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, authenticatedUser);
        
        HttpResponse httpResponse = httpRequest.execute();
        return ((ResponseWrapper<User>) httpResponse.parseAs(V2_USER_TYPE)).getData();
    }
    
    @Override
    @SneakyThrows(IOException.class)
    public List<Workout> getWorkoutForUser(Date date, double tzOffset, AuthenticatedUser authenticatedUser)
    {
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String url = String.format(V2_WORKOUT_URL_TEMPLATE, authenticatedUser.getUserId(), formattedDate, tzOffset);
        HttpRequest httpRequest = createAuthenticatedGetRequest(url, authenticatedUser);

        HttpResponse httpResponse = httpRequest.execute();
        return ((ResponseWrapper<List<Workout>>) httpResponse.parseAs(V2_WORKOUT_TYPE)).getData();
    }

    @SneakyThrows(IOException.class)
    private String getCsrfToken()
    {
        HttpRequest httpRequest = createRequest(HttpMethods.HEAD, HOST, null);
        HttpResponse httpResponse = httpRequest.execute();
        String csrfToken = HttpUtils.getResponseCookie(httpResponse, CSRFTOKEN_COOKIE_NAME);
        return csrfToken;
    }

    private HttpRequest createAuthenticatedGetRequest(String url, AuthenticatedUser authenticatedUser)
        throws IOException
    {
        return createAuthenticatedRequest(HttpMethods.GET, url, authenticatedUser, null);
    }

    private HttpRequest createAuthenticatedRequest(String method, String url, AuthenticatedUser authenticatedUser,
       HttpContent httpContent) throws IOException
    {
        HttpRequest httpRequest = createRequest(method, url, httpContent);
        HttpHeaders headers = new HttpHeaders();
        headers.setCookie(
            SESSIONID_COOKIE_NAME + '=' + authenticatedUser.getSessionId() + ';' +
            CSRFTOKEN_COOKIE_NAME + '=' + authenticatedUser.getCsrfToken()
        );
        // NOTE: csrftoken doesn't seem to be necessary after authentication, but including for future proofing
        httpRequest.setHeaders(headers);
        return httpRequest;
    }

    private HttpRequest createRequest(String method, String url, HttpContent httpContent) throws IOException
    {
        GenericUrl genericUrl = new GenericUrl(url);
        HttpRequest httpRequest = requestFactory.buildRequest(method, genericUrl, httpContent);
        return httpRequest;
    }
}