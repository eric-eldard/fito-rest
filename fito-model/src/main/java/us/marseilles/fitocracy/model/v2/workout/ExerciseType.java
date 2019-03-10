package us.marseilles.fitocracy.model.v2.workout;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Wrapper for an {@link Exercise} performed during a {@link Workout}
 */
@Getter
@Setter
public class ExerciseType extends RootGroupChild
{
    public static final String TYPE = "exercise";
    
    @Key
    private Exercise exercise;
}