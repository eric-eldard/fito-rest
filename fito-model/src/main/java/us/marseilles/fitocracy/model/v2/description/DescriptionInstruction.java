package us.marseilles.fitocracy.model.v2.description;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Text description of how to perform an exercise
 */
@Getter
@Setter
public class DescriptionInstruction
{
    @Key
    private String value;
}