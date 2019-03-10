package us.marseilles.fitocracy.model.v2.user;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Text and url for this user to invite a new user to Fitocracy
 */
@Getter
@Setter
public class Invite
{
    @Key("invite_text")
    private String inviteText;

    @Key("invite_url")
    private String inviteUrl;
}
