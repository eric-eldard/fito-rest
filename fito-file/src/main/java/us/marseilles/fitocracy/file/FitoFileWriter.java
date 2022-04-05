package us.marseilles.fitocracy.file;

import java.util.Collection;
import java.util.List;

import us.marseilles.fitocracy.model.v1.ActivityWorkout;

public interface FitoFileWriter
{
    String ACTIVITIES_FILE_NAME = "fito_activities";
    
    /**
     * Produce a file from a history of activities.
     *
     * File name will conform to the pattern "{destinationDir}/{@link #ACTIVITIES_FILE_NAME}.{extension}"
     * 
     * @param activityHistory a collection of {@link ActivityWorkout}s to write to file
     * @param destinationDir the directory to write the file to
     * @param includeNulls pass <tt>true</tt> to include keys with null values or <tt>false</tt> for smaller file size
     * @param pretty pass <tt>true</tt> for pretty (easier to read) or <tt>false</tt> for compact (smaller file size)
     *
     * @throws java.io.IOException (unchecked) when error converting to JSON or writing file to disk
     */
    void writeActivityHistory(Collection<List<ActivityWorkout>> activityHistory, String destinationDir,
                              boolean includeNulls, boolean pretty);
}