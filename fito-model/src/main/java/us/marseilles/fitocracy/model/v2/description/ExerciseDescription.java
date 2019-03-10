package us.marseilles.fitocracy.model.v2.description;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * The description of a single activity, as modeled by the v2 API
 */
@Getter
@Setter
public class ExerciseDescription
{
    /**
     * Other names the exercise is know by, as strings
     */
    @Key
    private List<String> aliases;
    
    @Key
    private List<DescriptionInput> inputs;

    @Key
    private List<DescriptionInstruction> instructions;
    
    @Key("is_hidden")
    private boolean isHidden;
    
    @Key("is_popular")
    private boolean isPopular;
    
    @Key
    private DescriptionMedia media;

    @Key
    private String name;

    @Key
    private List<Tag> tags;
}