package us.marseilles.fitocracy.rest.domain;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * A Fitocracy user
 */
@Getter
@Setter
public class User
{
    @Key
    private long id;
    
    @Key
    private String username;
    
    @Key
    private String title;
    
    @Key
    private long points;
    
    @Key
    private int level;

    @Key
    private boolean hero;
    
    @Key
    private String pic;
    
    @Key
    private String info;
    
    @Key
    private boolean imperial;
}