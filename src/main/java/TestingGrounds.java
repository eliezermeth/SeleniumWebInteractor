import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestingGrounds
{
    public static void main(String[] args)
    {
        //new TouroHeathForm();
        TimeJob timeJob = new TimeJob(23, 59, 00);
        //timeJob.setStartTime(10, 9, 30);
        timeJob.startJob();
    }
}
