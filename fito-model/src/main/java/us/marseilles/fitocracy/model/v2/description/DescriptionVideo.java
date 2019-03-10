package us.marseilles.fitocracy.model.v2.description;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Video depiction of an exercise being performed
 */
@Getter
@Setter
public class DescriptionVideo
{
    @Key("youtube_embed")
    private String youtubeEmbed;
}