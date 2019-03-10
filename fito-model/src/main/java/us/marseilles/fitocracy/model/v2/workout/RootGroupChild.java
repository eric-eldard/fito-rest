package us.marseilles.fitocracy.model.v2.workout;

import com.google.api.client.json.JsonPolymorphicTypeMap;
import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;
import us.marseilles.fitocracy.model.v2.FitoV2Entity;

/**
 * API v2
 * <p>
 * A child of a {@link RootGroup}: either a {@link Group} or an ungrouped {@link ExerciseType}
 */
@Getter
@Setter
public abstract class RootGroupChild extends FitoV2Entity
{
    @JsonPolymorphicTypeMap(typeDefinitions = {
        @JsonPolymorphicTypeMap.TypeDef(key = Group.TYPE, ref = Group.class),
        @JsonPolymorphicTypeMap.TypeDef(key = ExerciseType.TYPE, ref = ExerciseType.class)
    })
    @Key
    private String type;
}