// This is the Context (Account):
// Maintains an instance of a ConcreteState subclass that defines the current state

public class GumballMachine {

    private GumballMachineState NoGumballs_NoQuarterInSlot = new NoGumballs_NoQuarterInSlot_State();
    private GumballMachineState NoGumballs_QuarterInSlot = new NoGumballs_QuarterInSlot_State();
    private GumballMachineState Gumballs_NoQuarterInSlot = new Gumballs_NoQuarterInSlot_State();
    private GumballMachineState Gumballs_QuarterInSlot = new Gumballs_QuarterInSlot_State();

    // Actual Gumball Machine State, changes its state depending on functions called.
    GumballMachineState state;
    // Initial amounts in the Machine, subject to change/modified by use of the machine.
    int numGumballs;
    double machineTotalMoney;

    // Constructor
    public GumballMachine() {
        // Initialize
        state = Gumballs_NoQuarterInSlot;
        numGumballs = 2;
        machineTotalMoney = 2.00;
    }

    /**
     * Adds gumballs to the machine
     */
    public void setHasGumballs() {
        System.out.println("Nice! You are adding gumballs to the Gumball Machine.");
        System.out.println("Adding " + 10 + " gumball(s) to the gumball machine.");
        numGumballs = numGumballs + 10;
        System.out.println("There are now " + numGumballs + " in gumball machine.");
        // No quarter in slot
        if ((state.getClass() == NoGumballs_NoQuarterInSlot.getClass()) || (state.getClass() == Gumballs_NoQuarterInSlot.getClass())) {
            state = Gumballs_NoQuarterInSlot;
        }
        // Quarter in slot
        else {
            state = Gumballs_QuarterInSlot;
        }
    }

    /**
     * Inserts a quarter into the slot
     */
    public void setHasQuarter() {
        System.out.println("Ohhhh shiny. Thanks for inserting a quarter!");
        if(state.getClass() == Gumballs_NoQuarterInSlot.getClass()) {
            state = Gumballs_QuarterInSlot;
        }
        else {
            state = NoGumballs_QuarterInSlot;
        }
    }

    /**
     * removes the quarter currently in the slot (user changed their mind)
     */
    public void setHasNoQuarter() {
        System.out.println("Oh no. I needed that quarter from you! Oh well...");
        if (state.getClass() == Gumballs_QuarterInSlot.getClass()) {
            state = Gumballs_NoQuarterInSlot;
        }
        else {
            state = NoGumballs_NoQuarterInSlot;
        }
    }


    // Consumes quarter and dispenses gumball. Also adds to machine's total money.
    public void setHandleTurned() {
        System.out.println("HERE IS YOUR GUMBALL!!! YAY!!!");
        --numGumballs;
        machineTotalMoney = machineTotalMoney + 0.25;
        if (numGumballs > 0) {
            state = Gumballs_NoQuarterInSlot;
        }
        else {
            state = NoGumballs_NoQuarterInSlot;
        }
    }

    public void addGumball(GumballMachine g) {
        state.addGumball(g);
    }

    public void insertQuarter(GumballMachine g) {
        state.insertQuarter(g);
    }

    public void removeQuarter(GumballMachine g) {
        state.removeQuarter(g);
    }

    public void turnHandle(GumballMachine g) {
        state.turnHandle(g);
    }
}
