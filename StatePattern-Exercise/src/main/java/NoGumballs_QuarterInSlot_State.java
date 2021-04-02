public class NoGumballs_QuarterInSlot_State implements GumballMachineState {
  public void addGumball(GumballMachine g) {
    g.setHasGumballs();
  }

  public void insertQuarter(GumballMachine g) {
    System.out.println("I already have a quarter, try turning the handle.");
  }

  public void removeQuarter(GumballMachine g) {
    g.setHasNoQuarter();
  }

  public void turnHandle(GumballMachine g) {
    System.out.println("No Gumballs right now, come back later.");
  }
}
