package us.marseilles.fitocracy.model.v2.workout;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * A Fitocracy group (of {@link Exercise}s)
 */
@Getter
@Setter
public class Group extends RootGroupChild
{
    public static final String TYPE = "group";
    
    @Key
    private List<Exercise> children;

    /**
     * "Group A", "Group B", etc.
     */
    @Key
    private String name;
}