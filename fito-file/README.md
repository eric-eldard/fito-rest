# fito-file

## About
Java tools for reading and writing Fitocracy.com REST API data from/to various file formats.

## Build
`mvn clean package`

## Use
Add dependency
```xml
<dependency>
  <groupId>us.marseilles.fitocracy</groupId>
  <artifactId>fito-file</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Read from any format into the fito-model, then write to any format.
```java
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

```

## TODO
* Reduce file size by removing redundant data
* CSV reader/writer