package us.marseilles.fitocracy.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

@UtilityClass
public class FileUtils
{
    public void writeToFile(String path, String content) throws IOException
    {
        File file = new File(path);
        try (FileOutputStream output = new FileOutputStream(file))
        {
            IOUtils.write(content.getBytes(), output);
        }
    }
}