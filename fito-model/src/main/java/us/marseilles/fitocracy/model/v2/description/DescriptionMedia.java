package us.marseilles.fitocracy.model.v2.description;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Image and video depictions of an exercise
 */
@Getter
@Setter
public class DescriptionMedia
{
    @Key
    private DescriptionImage image;

    @Key
    private DescriptionVideo video;
}