package us.marseilles.fitocracy.model.v2.workout;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;
import us.marseilles.fitocracy.model.v2.HasCommentCount;
import us.marseilles.fitocracy.model.v2.HasPropCount;
import us.marseilles.fitocracy.model.v2.user.UserAchievement;

/**
 * API v2
 * <p>
 * A Fitocracy user achievement, when attached to a {@link Workout} via {@link Awards}
 *
 * @see UserAchievement
 */
@Getter
@Setter
public class WorkoutAchievement extends FitoV2Entity implements HasCommentCount, HasPropCount
{
    @Key("comment_count")
    private short commentCount;

    @Key
    private String description;

    @Key("entry_id")
    private long entryId;

    /**
     * Achievement badge image
     */
    @Key("image_url")
    private String imageUrl;

    @Key("prop_count")
    private int propCount;
    
    /**
     * Name of the achievement
     */
    @Key
    private String title;

    /**
     * Always "achievement"
     */
    @Key
    private String type;
}