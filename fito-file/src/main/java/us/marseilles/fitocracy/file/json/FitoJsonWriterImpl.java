package us.marseilles.fitocracy.file.json;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import us.marseilles.fitocracy.file.FitoFileWriter;
import us.marseilles.fitocracy.file.util.FileUtils;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;

/**
 * Writes fito-model entities to JSON files
 */
public class FitoJsonWriterImpl implements FitoFileWriter
{
    @Override
    @SneakyThrows(IOException.class)
    public void writeActivityHistory(Collection<List<ActivityWorkout>> activityHistory, String destinationDir,
                                     boolean includeNulls, boolean pretty)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();

        if (includeNulls) { gsonBuilder.serializeNulls(); }
        if (pretty) { gsonBuilder.setPrettyPrinting(); }

        String json = gsonBuilder.create().toJson(activityHistory);

        boolean destDirEndsInSlash = destinationDir.endsWith(File.separator);
        String fileName = destinationDir + (destDirEndsInSlash ? "" : File.separator) + ACTIVITIES_FILE_NAME +  ".json";
        FileUtils.writeToFile(fileName, json);
    }
}