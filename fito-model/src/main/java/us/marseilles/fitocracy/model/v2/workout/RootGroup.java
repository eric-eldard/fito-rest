package us.marseilles.fitocracy.model.v2.workout;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;
import us.marseilles.fitocracy.model.v2.description.ExerciseDescription;

/**
 * API v2
 * <p>
 * The parent of all {@link Group}s and ungrouped {@link ExerciseDescription}s for a {@link Workout}
 */
@Getter
@Setter
public class RootGroup extends FitoV2Entity
{
    @Key
    private List<RootGroupChild> children;

    /**
     * Always "Workout"
     */
    @Key
    private String name;

    @Key
    private String notes;

    /**
     * Always "group"
     */
    @Key
    private String type;
}