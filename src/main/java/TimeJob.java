import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarInputStream;

public class TimeJob
{
    private int hour = 23;
    private int minute = 43;
    private int second = 00;

    protected Timer timer;

    public TimeJob(int hour, int minute, int second)
    {
        setStartTime(hour, minute, second);
    }

    public static void main(String[] args)
    {
        TimeJob timeJob = new TimeJob(06, 00, 00); // 6 AM
        timeJob.startJob();
    }

    /**
     * Set the start time in 24-hour time.
     * @param hour
     * @param minute
     * @param second
     */
    public void setStartTime(int hour, int minute, int second)
    {
        this.hour = hour % 24; // 0-23 permitted
        this.minute = minute % 60; // 0-59 permitted
        this.second = second % 60; // 0-59 permitted
    }

    /**
     * Sets the job as ready to start at the input time.
     */
    public void startJob()
    {
        timer = new Timer();
        Date date = createDate();
        timer.schedule(new MyTimerTask(), date);

        System.out.println("Scheduled timer to start job at " + date);
    }

    /**
     * Make the hour, minute, and second into a Calendar instance.
     * @return
     */
    public Date createDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        // make sure that event is in the future
        if (calendar.before(Calendar.getInstance()))
            calendar.add(Calendar.DATE, 1); // add 1 day

        return calendar.getTime();
    }

    /**
     * Timer for when to run the Touro Health form.
     */
    class MyTimerTask extends TimerTask
    {
        @Override
        public void run() {
            new TouroHeathForm();
            timer.cancel();
        }
    }
}

