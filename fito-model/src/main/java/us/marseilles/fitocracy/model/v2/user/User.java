package us.marseilles.fitocracy.model.v2.user;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;

/**
 * API v2
 * <p>
 * A Fitocracy user modeled in the v2 API
 */
@Getter
@Setter
public class User extends FitoV2Entity
{
    @Key
    private List<UserAchievement> achievements;

    @Key
    private byte age;

    @Key("bio_text")
    private String bioText;

    @Key("days_on_fitocracy")
    private short daysOnFitocracy;

    /**
     * Always true when requesting user is this user
     */
    @Key("followed_by_request_user")
    private boolean followedByRequestUser;

    @Key("follower_count")
    private int followerCount;

    /**
     * Always true when requesting user is this user
     */
    @Key("follows_request_user")
    private boolean followsRequestUser;

    @Key
    private String gender;

    /**
     * Seems to always return 0
     */
    @Key("height_in_inches")
    private byte heightInInches;

    @Key
    private Invite invite;

    @Key("last_90_day_points")
    private int last90DayPoints;

    @Key("last_month_points")
    private int lastMonthPoints;

    @Key("last_week_points")
    private int lastWeekPoints;

    @Key
    private short level;

    @Key("marketing_attributes")
    private MarketingAttributes marketingAttributes;

    @Key("need_email_update")
    private boolean needEmailUpdate;

    @Key("number_of_groups")
    private short numberOfGroups;

    @Key("number_of_points_towards_next_level")
    private int numberOfPointsTowardsNextLevel;

    @Key("number_of_points_until_next_level")
    private int numberOfPointsUntilNextLevel;

    @Key("number_of_quests")
    private short numberOfQuests;

    @Key("points_overall")
    private int pointsOverall;

    @Key("profile_picture_url")
    private String profilePictureUrl;

    @Key("show_bota_promotion")
    private boolean showBotaPromotion;

    @Key
    private String title;

    @Key("total_points_for_next_level")
    private int totalPointsForNextLevel;

    /**
     * Yes...String
     */
    @Key("total_workouts")
    private String totalWorkouts;

    @Key("user_type")
    private String userType;

    @Key
    private String username;
}