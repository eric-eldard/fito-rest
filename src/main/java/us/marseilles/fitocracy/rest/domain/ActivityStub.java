package us.marseilles.fitocracy.rest.domain;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * A minimal representation of a user's record for a single exercise; returned in list of activities to be used for
 * making follow-up calls about specific activities.
 */
@Getter
@Setter
public class ActivityStub
{
    @Key
    private int id;

    @Key
    private int count;

    @Key
    private String name;
}