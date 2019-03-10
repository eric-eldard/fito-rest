package us.marseilles.fitocracy.model.v2.description;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Still image depiction of an exercise being performed
 */
@Getter
@Setter
public class DescriptionImage
{
    @Key("image_full_hash")
    private String imageFullHash;

    @Key("image_full_url")
    private String imageFullUrl;

    @Key("image_medium_hash")
    private String imageMediumHash;

    @Key("image_medium_url")
    private String imageMediumUrl;

    @Key("image_thumb_hash")
    private String imageThumbHash;

    @Key("image_thumb_url")
    private String imageThumbUrl;
}