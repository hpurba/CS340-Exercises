public class Gumballs_NoQuarterInSlot_State implements GumballMachineState {
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
    System.out.println("No quarter, no gumball. Try inserting a quarter!");
  }
}
