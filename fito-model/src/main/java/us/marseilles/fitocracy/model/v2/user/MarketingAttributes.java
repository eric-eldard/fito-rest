package us.marseilles.fitocracy.model.v2.user;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 */
@Getter
@Setter
public class MarketingAttributes
{
    @Key
    private AppboyMarketingAttributes appboy;
}