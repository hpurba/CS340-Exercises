package Part1;

public class GymImpl implements Gym {
    int numReps = 0;

    public GymImpl() {
        System.out.println("Welcome to the Gym!");
    }

    @Override
    public void workout() {
        System.out.println("Woah, that's a heavy set!");
        numReps++;
    }

    @Override
    public void liftCount() {
        System.out.println("Lift count: " + numReps + ".");
    }
}
