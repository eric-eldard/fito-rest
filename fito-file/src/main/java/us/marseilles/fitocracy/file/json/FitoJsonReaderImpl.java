package us.marseilles.fitocracy.file.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.SneakyThrows;
import us.marseilles.fitocracy.file.FitoFileReader;

/**
 * Reads JSON files into fito-model entities
 */
public class FitoJsonReaderImpl implements FitoFileReader
{
    private JsonFactory jsonFactory = new JacksonFactory();
    
    @Override
    @SneakyThrows(IOException.class)
    public ActivityHistory readActivityHistory(String path)
    {
        List activityWorkouts;
        try (FileInputStream inStream = new FileInputStream(path))
        {
            activityWorkouts = jsonFactory.fromInputStream(inStream, ActivityHistory.class);
        }
        return (ActivityHistory) activityWorkouts;
    }
}