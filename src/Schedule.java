import java.util.Timer;
import java.util.TimerTask;

public class Schedule {

    private final Sorter sorter = new Sorter();
    public void test(){
        new Timer().scheduleAtFixedRate(print(),0L, 2000L);
    }
    private TimerTask print(){
        return new TimerTask() {
            @Override
            public void run() {
                sorter.sorting();
            }
        };
    }

}
