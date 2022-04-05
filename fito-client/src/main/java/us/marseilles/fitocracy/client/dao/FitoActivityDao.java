package us.marseilles.fitocracy.client.dao;

import java.util.Date;
import java.util.List;

import us.marseilles.fitocracy.model.identity.AuthenticatedUser;
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
     * Retrieve a userId and sessionId by logging in.
     *
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    AuthenticatedUser authenticate(String username, String password);

    /**
     * API v1
     * 
     * @param authenticatedUser see {@link #authenticate(String, String)}
     *
     * @return minimal info on all activities for user
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<ActivityStub> getAllActivitiesForUser(AuthenticatedUser authenticatedUser);

    /**
     * API v1
     * 
     * @param activityId id of activity to retrieve; get list from {@link #getAllActivitiesForUser(AuthenticatedUser)}
     * @param authenticatedUser see {@link #authenticate(String, String)}
     *
     * @return the complete history of a single activity for the given user
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<ActivityWorkout> getActivityForUser(long activityId, AuthenticatedUser authenticatedUser);

    /**
     * API v2
     * 
     * @param authenticatedUser see {@link #authenticate(String, String)}
     * 
     * @return the complete details for all exercises in Fitocracy
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<ExerciseDescription> getAllExerciseDescriptions(AuthenticatedUser authenticatedUser);

    /**
     * API v2
     *
     * @param authenticatedUser see {@link #authenticate(String, String)}
     *
     * @return details of the matching user
     *
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    User getUser(AuthenticatedUser authenticatedUser);

    /**
     * API v2
     * 
     * @param date UTC date of the workout to be retrieved
     * @param tzOffset UTC offset, in hours, to be applied to the date
     * @param authenticatedUser see {@link #authenticate(String, String)}
     * 
     * @return the complete details of the user's workouts for the given date; returns empty data if no workout for date
     * 
     * @throws java.io.IOException (unchecked) if Fitocracy.com cannot be reached
     */
    List<Workout> getWorkoutForUser(Date date, double tzOffset, AuthenticatedUser authenticatedUser);
}