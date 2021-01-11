import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeJob
{
    private int hour = 22;
    private int minute = 24;
    private int second = 30;

    protected Timer timer;

    public void setStartTime(int hour, int minute, int second)
    {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void startJob()
    {
        timer = new Timer();

        timer.schedule(new MyTimerTask(), createDate());
    }

    public Date createDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        return calendar.getTime();
    }

    class MyTimerTask extends TimerTask
    {
        @Override
        public void run() {
            new TouroHeathForm();
            timer.cancel();
        }
    }
}

