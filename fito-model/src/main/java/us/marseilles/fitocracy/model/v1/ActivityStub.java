package us.marseilles.fitocracy.model.v1;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v1
 * <p>
 * A minimal representation of a user's record for a single exercise; returned in list of activities to be used for
 * making follow-up calls about specific activities.
 */
@Getter
@Setter
public class ActivityStub extends FitoV1Entity
{
    /**
     * Number of times the user has performed the activity
     */
    @Key
    private int count;

    /**
     * Name of the activity
     */
    @Key
    private String name;
}