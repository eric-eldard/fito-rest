package us.marseilles.fitocracy.model.v2.workout;

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
 * A Fitocracy user quest, when attached to a {@link Workout} via {@link Awards}
 */
@Getter
@Setter
public class WorkoutQuest extends FitoV2Entity implements HasCommentCount, HasPoints, HasPropCount
{
    @Key("comment_count")
    private short commentCount;

    @Key
    private String description;

    @Key("entry_id")
    private long entryId;

    /**
     * Quest badge image
     */
    @Key("image_url")
    private String imageUrl;

    @Key
    private int points;

    @Key("prop_count")
    private int propCount;

    /**
     * Name of the quest
     */
    @Key
    private String title;

    /**
     * Always "quest"
     */
    @Key
    private String type;
}