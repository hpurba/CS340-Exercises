public class NoGumballs_NoQuarterInSlot_State implements GumballMachineState {

  // adds more gumballs to the machine
  public void addGumball(GumballMachine g) {
    g.setHasGumballs();
  }

  public void insertQuarter(GumballMachine g) {
    g.setHasQuarter();
  }

  public void removeQuarter(GumballMachine g) {
    System.out.println("I don't have a quarter to give back to you.");
  }

  public void turnHandle(GumballMachine g) {
    System.out.println("No quarter, no gumball. Also, no gumballs to dispense, sorry!");
  }
}
