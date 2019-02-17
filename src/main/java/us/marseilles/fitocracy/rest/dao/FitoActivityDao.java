package us.marseilles.fitocracy.rest.dao;

import java.io.IOException;
import java.util.List;

import us.marseilles.fitocracy.rest.domain.ActivityWorkout;
import us.marseilles.fitocracy.rest.domain.ActivityStub;

public interface FitoActivityDao
{
    /**
     * @param username the user to retrieve the ID for
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie)
     *
     * @return the Fitocracy user ID corresponding to the given username
     */
    String getUserId(String username, String sessionId) throws IOException;

    /**
     * @param userId the user to retrieve all activities for; get for username with {@link #getUserId(String, String)}
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie)
     *
     * @return minimal info on all activities for user
     */
    List<ActivityStub> getAllActivitiesForUser(String userId, String sessionId) throws IOException;

    /**
     * @param activityId id of activity to retrieve; get id list from {@link #getAllActivitiesForUser(String, String)}
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie); this is also the
     *                  user identifier for this request
     *
     * @return the complete history of a single activity for the given user
     */
    List<ActivityWorkout> getActivityForUser(int activityId, String sessionId) throws IOException;
}