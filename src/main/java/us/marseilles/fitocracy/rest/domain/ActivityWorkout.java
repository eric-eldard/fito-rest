package us.marseilles.fitocracy.rest.domain;

import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * A collection of exercise sets which belong to the same workout
 */
@Getter
@Setter
public class ActivityWorkout
{
    @Key
    private String id;
    
    @Key
    private DateTime date;
    
    @Key("actions")
    private List<ActivitySet> activitySets;
}