package us.marseilles.fitocracy.model.v2.workout;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;

/**
 * API v2
 * <p>
 * A dimension for describing an {@link ExerciseSet}, like weight or number of reps
 */
@Getter
@Setter
public class SetInput extends FitoV2Entity
{
    /**
     * Where this input is ordered in relation to other inputs for the same set
     */
    @Key("input_ordinal")
    private byte inputOrdinal;

    /**
     * A string value like "weight" or "reps"
     */
    @Key
    private String type;

    /**
     * A string value like "lb" or "reps"
     */
    @Key
    private String unit;

    /**
     * The quantity (of pounds, reps, etc.)
     */
    @Key
    private short value;
}