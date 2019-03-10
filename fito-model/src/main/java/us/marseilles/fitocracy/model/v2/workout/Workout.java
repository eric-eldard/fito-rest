package us.marseilles.fitocracy.model.v2.workout;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;
import us.marseilles.fitocracy.model.v2.HasCommentCount;
import us.marseilles.fitocracy.model.v2.HasPoints;
import us.marseilles.fitocracy.model.v2.HasPropCount;

/**
 * API v2
 * <p>
 * One Fitocracy workout
 */
@Getter
@Setter
public class Workout extends FitoV2Entity implements HasCommentCount, HasPoints, HasPropCount
{
    @Key
    private Awards awards;

    @Key("comment_count")
    private short commentCount;

    @Key
    private int points;

    @Key("prop_count")
    private int propCount;

    @Key("root_group")
    private RootGroup rootGroup;

    @Key("share_url")
    private String shareUrl;

    @Key
    private boolean submitted;

    @Key("updated_timestamp")
    private DateTime updatedTimestamp;

    @Key("workout_timestamp")
    private DateTime workoutTimestamp;
}