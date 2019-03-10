package us.marseilles.fito.sample;

import us.marseilles.fitocracy.file.FitoFileReader;
import us.marseilles.fitocracy.file.FitoFileWriter;
import us.marseilles.fitocracy.file.json.FitoJsonReaderImpl;
import us.marseilles.fitocracy.file.json.FitoJsonWriterImpl;

public class FileSample
{
    public static void main(String[] args)
    {
        String source = "/path/to/existing/history/file/fito_activity_history.json";
        String destination = "/path/to/write/history/file";
        
        FitoFileReader fitoJsonReader = new FitoJsonReaderImpl();
        FitoFileWriter fitoJsonWriter = new FitoJsonWriterImpl();
        
        FitoFileReader.ActivityHistory activityWorkouts = fitoJsonReader.readActivityHistory(source);
        fitoJsonWriter.writeActivitiesHistory(activityWorkouts, destination, true);
    }
}