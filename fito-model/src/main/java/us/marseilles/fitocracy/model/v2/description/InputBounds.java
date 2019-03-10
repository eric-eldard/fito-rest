package us.marseilles.fitocracy.model.v2.description;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Min and max allowed values for a {@link DescriptionInput}
 */
@Getter
@Setter
public class InputBounds
{
    /**
     * Largest max value = 999999999999...go figure
     */
    @Key
    private long maximum;

    /**
     * 0 or 1
     */
    @Key
    private byte minimum;
}