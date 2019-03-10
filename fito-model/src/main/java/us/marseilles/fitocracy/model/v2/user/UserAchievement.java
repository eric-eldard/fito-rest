package us.marseilles.fitocracy.model.v2.user;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;
import us.marseilles.fitocracy.model.v2.workout.WorkoutAchievement;

/**
 * API v2
 * <p>
 * A Fitocracy user achievement, when attached to a {@link User}
 *
 * @see WorkoutAchievement
 */
@Getter
@Setter
public class UserAchievement extends FitoV2Entity
{
    @Key
    private String description;

    /**
     * Achievement badge image
     */
    @Key("image_url")
    private String imageUrl;

    /**
     * Name of the achievement
     */
    @Key
    private String name;

    @Key
    private short type;
}