package us.marseilles.fitocracy.model.v1;

import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v1
 * <p>
 * A collection of exercise sets which belong to the same workout
 */
@Getter
@Setter
public class ActivityWorkout
{
    /**
     * id is the workout date in the format "yyyy-MM-dd"
     */
    @Key
    private String id;
    
    @Key
    private DateTime date;
    
    @Key("actions")
    private List<ActivitySet> activitySets;
}