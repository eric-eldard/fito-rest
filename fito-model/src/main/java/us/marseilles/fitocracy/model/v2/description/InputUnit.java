package us.marseilles.fitocracy.model.v2.description;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * A dimension used for describing a {@link DescriptionInput}
 */
@Getter
@Setter
public class InputUnit
{
    /**
     * Percentage of a pound for lbs (1) and kgs (.45[...]) or 1 if unit is "reps"
     */
    @Key("conversion_factor")
    private double conversionFactor;

    /**
     * "lb", "kg", "reps", etc.
     */
    @Key
    private String name;
}
