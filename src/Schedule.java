import java.util.Timer;
import java.util.TimerTask;

public class Schedule {

    private final Sorter sorter = new Sorter();
    public void activeTimer(byte choose, String path, long period){
        new Timer().scheduleAtFixedRate(task(choose, path),0L, period);
    }
    private TimerTask task(byte choose, String path){
        return new TimerTask() {
            @Override
            public void run() {
                sorter.sorting(choose, path);
            }
        };
    }

}
