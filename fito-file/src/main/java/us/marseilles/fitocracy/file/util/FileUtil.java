package us.marseilles.fitocracy.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public final class FileUtil
{
    public static void writeToFile(String path, String content) throws IOException
    {
        File file = new File(path);
        try (FileOutputStream output = new FileOutputStream(file))
        {
            IOUtils.write(content.getBytes(), output);
        }
    }

    private FileUtil()
    {
    }
}