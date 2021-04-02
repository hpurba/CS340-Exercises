// All the ways the user is able to work with the Gumball Machine
public interface GumballMachineState {
    // Different states expected
    void addGumball(GumballMachine g);        // adds more gumballs to the machine
    void insertQuarter(GumballMachine g);               // inserts a quarter into the slot
    void removeQuarter(GumballMachine g);               // removes the quarter currently in the slot (user changed their mind)
    void turnHandle(GumballMachine g);                  // consumes quarter and dispenses gumballs
}
