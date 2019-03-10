package us.marseilles.fitocracy.model.v1;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v1
 * <p>
 * A unit of measure, like kg or reps
 */
@Getter
@Setter
public class Unit extends FitoV1Entity
{
    @Key
    private String name;
    
    @Key
    private String abbr;
}