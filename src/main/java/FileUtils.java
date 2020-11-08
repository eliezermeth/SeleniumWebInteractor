import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils
{
    public static String[] readFile(String filename)
    {
        String[] data = new String[4];

        try
        {
            Scanner in = new Scanner(new File(filename)).useDelimiter("\n");

            for (int i = 0; i < data.length; i++)
            {
                data[i] = in.next();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return data;
    }
}
