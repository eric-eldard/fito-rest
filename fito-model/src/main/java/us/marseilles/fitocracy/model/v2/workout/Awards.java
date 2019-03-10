package us.marseilles.fitocracy.model.v2.workout;

import java.util.List;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * {@link WorkoutAchievement}s and {@link WorkoutQuest}s earned during a {@link Workout}
 */
@Getter
@Setter
public class Awards
{
    @Key
    private List<WorkoutAchievement> achievements;

    @Key
    private List<WorkoutQuest> quests;
}