package us.marseilles.fitocracy.file.json;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import us.marseilles.fitocracy.file.FitoFileReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reads JSON files into fito-model entities
 */
public class FitoJsonReaderImpl implements FitoFileReader
{
    @Override
    @SneakyThrows(IOException.class)
    public ActivityHistory readActivityHistory(String path)
    {
        ActivityHistory activityHistory;
        Gson gson = new Gson();
        try (FileInputStream inStream = new FileInputStream(path))
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            activityHistory = gson.fromJson(reader, ActivityHistory.class);
        }
        return activityHistory;
    }
}