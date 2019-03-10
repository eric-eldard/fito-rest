package us.marseilles.fitocracy.file.json;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.SneakyThrows;
import us.marseilles.fitocracy.file.FitoFileWriter;
import us.marseilles.fitocracy.file.util.FileUtil;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;

/**
 * Writes fito-model entities to JSON files
 */
public class FitoJsonWriterImpl implements FitoFileWriter
{
    private JsonFactory jsonFactory = new JacksonFactory();

    @Override
    @SneakyThrows(IOException.class)
    public void writeActivitiesHistory(Collection<List<ActivityWorkout>> activitiesHistory, String destinationDir, 
        boolean pretty)
    {
        String json = pretty ?
            jsonFactory.toPrettyString(activitiesHistory) :
            jsonFactory.toString(activitiesHistory);

        boolean destDirEndsInSlash = destinationDir.endsWith(File.separator);
        String fileName = destinationDir + (destDirEndsInSlash ? "" : File.separator) + ACTIVITIES_FILE_NAME +  ".json";
        FileUtil.writeToFile(fileName, json);
    }
}