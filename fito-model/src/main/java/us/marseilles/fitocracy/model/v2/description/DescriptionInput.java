package us.marseilles.fitocracy.model.v2.description;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;

/**
 * API v2
 * <p>
 * A dimension for describing an exercise, like weight or reps 
 */
@Getter
@Setter
public class DescriptionInput extends FitoV2Entity
{
    @Key("allowed_units")
    private List<InputUnit> allowUnits;

    @Key
    private InputBounds bounds;

    /**
     * Example: "Weight"
     */
    @Key("display_name")
    private String displayName;

    @Key("hidden_by_default")
    private boolean hiddenByDefault;

    /**
     * Example: "lb"
     */
    @Key("imperial_unit")
    private String imperialUnit;

    @Key("input_ordinal")
    private byte inputOrdinal;

    /**
     * Example: "kg"
     */
    @Key("metric_unit")
    private String metricUnit;

    /**
     * Example: "weight"
     */
    @Key
    private String type;
}