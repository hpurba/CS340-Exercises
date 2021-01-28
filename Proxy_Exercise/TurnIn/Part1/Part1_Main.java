package Part1;

public class Main {
    public static void main(String[] args) {
        Gym gym = new GymProxy();
        gym.workout();
        gym.liftCount();
        gym.workout();
        gym.workout();
        gym.liftCount();
    }
}
