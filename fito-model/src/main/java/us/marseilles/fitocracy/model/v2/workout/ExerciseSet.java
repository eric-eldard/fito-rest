package us.marseilles.fitocracy.model.v2.workout;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;

/**
 * API v2
 * <p>
 * A single set of an {@link Exercise} performed during a {@link Workout}
 */
@Getter
@Setter
public class ExerciseSet extends FitoV2Entity
{
    /**
     * A full text description of the {@link SetInput}s, like "105 lb x 6 reps"
     */
    @Key("description_string")
    private String descriptionString;

    @Key
    private List<SetInput> inputs;

    @Key("is_personal_record")
    private boolean isPersonalRecord;

    @Key
    private int points;
}