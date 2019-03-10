package us.marseilles.fitocracy.file;

import java.util.ArrayList;
import java.util.List;

import us.marseilles.fitocracy.model.v1.ActivityWorkout;

public interface FitoFileReader
{
    /**
     * Read a file from disk into the fito-model entities
     *
     * @param path to the file to read from
     *
     * @return history, modeled as a list of {@link ActivityWorkout}
     * 
     * @throws java.io.IOException (unchecked) when error reading file from disk
     */
    ActivityHistory readActivityHistory(String path);

    /**
     * Models this structure:
     * - List: All activities
     * -- List: All workout groupings of a single activity
     * --- ActivityWorkout: One workout's worth of the activity
     */
    class ActivityHistory extends ArrayList<List<ActivityWorkout>>
    {
    }
}