package us.marseilles.fitocracy.model.v2.user;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Attributes for Appboy marketing/tracking (https://www.braze.com/)
 */
@Getter
@Setter
public class AppboyMarketingAttributes
{
    @Key("_update_existing_only")
    private boolean updateExistingOnly;

    /**
     * User's Fitocracy bio
     */
    @Key
    private String bio;

    /**
     * Date of birth...yup...String ("yyyy-MM-dd")
     */
    @Key
    private String dob;

    /**
     * Fitocracy user id
     */
    @Key("external_id")
    private long externalId;

    @Key
    private String gender;

    @Key("goal_Set")
    private boolean goalSet;

    @Key
    private boolean hasandroid;

    /**
     * User's Fitocracy profile pic
     */
    @Key("image_url")
    private String imageUrl;

    @Key
    private String joindate;

    @Key
    private String lastwork;

    /**
     * User's Fitocracy level
     */
    @Key
    private short level;

    /**
     * User's Fitocracy user name
     */
    @Key
    private String username;

    /**
     * User's total Fitocracy workout count...as a String again
     */
    @Key
    private String workouts;
}