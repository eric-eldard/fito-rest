package us.marseilles.fitocracy.model.v2.workout;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.description.ExerciseDescription;

/**
 * API v2
 * <p>
 * An exercise performed during a workout
 */
@Getter
@Setter
public class Exercise
{
    /**
     * Matches ids of {@link ExerciseDescription}s
     */
    @Key
    private int exerciseId;

    /**
     * The name of the exercise
     */
    @Key
    private String name;

    /**
     * The user's notes for this exercise during this workout
     */
    @Key
    private String notes;

    @Key
    private List<ExerciseSet> sets;
}