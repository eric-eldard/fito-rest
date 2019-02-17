package us.marseilles.fitocracy.rest.domain;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * A unit of measure, like kg or reps
 */
@Getter
@Setter
public class Unit
{
    @Key
    private int id;
    
    @Key
    private String name;
    
    @Key
    private String abbr;
}