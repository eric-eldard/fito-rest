package us.marseilles.fitocracy.client.dao;

import java.util.Date;
import java.util.List;

import us.marseilles.fitocracy.model.v1.ActivityStub;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;
import us.marseilles.fitocracy.model.v2.description.ExerciseDescription;
import us.marseilles.fitocracy.model.v2.user.User;
import us.marseilles.fitocracy.model.v2.workout.Workout;

/**
 * Retrieves data from the v1 and v2 versions of the Fitocracy API
 */
public interface FitoActivityDao
{
    /**
     * API v1
     * 
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie)
     *
     * @return the Fitocracy user ID corresponding to the given sessionId
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     * @throws IllegalStateException if the provided sessionId is not valid
     */
    String getOwnUserId(String sessionId);

    /**
     * API v1
     * 
     * @param userId the user to retrieve all activities for; get for username with {@link #getOwnUserId(String)}
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie)
     *
     * @return minimal info on all activities for user
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<ActivityStub> getAllActivitiesForUser(String userId, String sessionId);

    /**
     * API v1
     * 
     * @param activityId id of activity to retrieve; get id list from {@link #getAllActivitiesForUser(String, String)}
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie); this is also the
     *                  user identifier for this request
     *
     * @return the complete history of a single activity for the given user
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<ActivityWorkout> getActivityForUser(long activityId, String sessionId);

    /**
     * API v2
     * 
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie)
     * 
     * @return the complete details for all exercises in Fitocracy
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<ExerciseDescription> getAllExerciseDescriptions(String sessionId);

    /**
     * API v2
     *
     * @param userId the user to retrieve; see {@link #getOwnUserId(String)}
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie)
     *
     * @return details of the matching user
     *
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    User getUser(String userId, String sessionId);

    /**
     * API v2
     * 
     * @param userId the user to retrieve the workout for; see {@link #getOwnUserId(String)}
     * @param date UTC date of the workout to be retrieved
     * @param tzOffset UTC offset, in hours, to be applied to the date
     * @param sessionId requires a sessionId from an authenticated user (from "sessionid" cookie)
     * 
     * @return the complete details of the user's workouts for the given date; returns empty data if no workout for date
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<Workout> getWorkoutForUser(String userId, Date date, double tzOffset, String sessionId);
}