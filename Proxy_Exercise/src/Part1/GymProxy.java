package Part1;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class GymProxy implements Gym {
    private Gym gymObj = new GymImpl();
    DayOfWeek[] AllowedDays = new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY};
    int[] AllowedTimeRange = new int[]{5, 23};

    @Override
    public void workout() {
        if(checkForOpen()){
            gymObj.workout();
        }
    }

    @Override
    public void liftCount() {
        if(checkForOpen()){
            gymObj.liftCount();
        }
    }

    public boolean checkForOpen(){
        LocalDateTime now = LocalDateTime.now();
        for(int i = 0; i < AllowedDays.length; i++) {
            if (now.getDayOfWeek() == AllowedDays[i]) {
                if (now.getHour() >= AllowedTimeRange[0] && now.getHour() <= AllowedTimeRange[1] ) {
                    return true;
                }
            }
        }
        return false;
    }
}
