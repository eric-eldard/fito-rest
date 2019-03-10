package us.marseilles.fitocracy.model.v2.description;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * Simple string-based tagging
 */
@Getter
@Setter
public class Tag
{
    /**
     * Values like:
     * - Barbell
     * - Beginner
     * - Chest
     * - Crossfit
     * - Strength
     */
    @Key
    private String name;

    /**
     * Values like:
     * - Equipment
     * - Experience Level
     * - Muscle Group
     * - Program
     * - Type [go figure]
     */
    @Key
    private String type;
}