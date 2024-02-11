import java.util.Timer;
import java.util.TimerTask;

public class Schedule {

    private final Sorter sorter = new Sorter();
    public void test(byte choose){
        new Timer().scheduleAtFixedRate(print(choose),0L, 2000L);
    }
    private TimerTask print(byte choose){
        return new TimerTask() {
            @Override
            public void run() {
                sorter.sorting(choose);
            }
        };
    }

}
