package us.marseilles.fitocracy.model.v1;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v1
 * <p>
 * A Fitocracy user modeled in the v1 API as a child of {@link ActivitySet}
 */
@Getter
@Setter
public class ActivitySetUser extends FitoV1Entity
{
    @Key
    private String username;
    
    @Key
    private String title;
    
    @Key
    private long points;
    
    @Key
    private short level;

    @Key
    private boolean hero;
    
    @Key
    private String pic;
    
    @Key
    private String info;
    
    @Key
    private boolean imperial;
}