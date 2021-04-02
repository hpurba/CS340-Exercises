public class Main {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine();

        // Try to get a gumball without a quarter
        System.out.println("------> Trying to get a gumball without paying for it:");
        gumballMachine.turnHandle(gumballMachine);
        System.out.println("\n");


        // Add a quarter to the machine and turn the handle
        System.out.println("------> Inserting the quarter and getting the gumball successfully:");
        gumballMachine.insertQuarter(gumballMachine);
        gumballMachine.turnHandle(gumballMachine);
        System.out.println("\n");

        // Add a quarter, then remove it, and then turn the handle
        System.out.println("------> Inserting a quarter, then taking it out, and tyring to get a gumball (unsuccessful you thief!)");
        gumballMachine.insertQuarter(gumballMachine);
        gumballMachine.removeQuarter(gumballMachine);
        gumballMachine.turnHandle(gumballMachine);
        System.out.println("\n");

        // Try and run the gum ball machine dry
        // Add a quarter to the machine and turn the handle
        System.out.println("------> Running the gumball machine out of gumballs:");
        gumballMachine.insertQuarter(gumballMachine);
        gumballMachine.turnHandle(gumballMachine);
        gumballMachine.insertQuarter(gumballMachine);
        gumballMachine.turnHandle(gumballMachine);
        gumballMachine.removeQuarter(gumballMachine);
        System.out.println("\n");

        // Add more gumballs to the machine
        System.out.println("------> Adding more gumballs to the machine:");
        gumballMachine.addGumball(gumballMachine);
        System.out.println("\n");

        // Add a quarter to the machine and turn the handle
        System.out.println("------> Inserting the quarter and getting the gumball successfully:");
        gumballMachine.insertQuarter(gumballMachine);
        gumballMachine.turnHandle(gumballMachine);
        System.out.println("\n");

        System.out.println("HAVE A NICE DAY!!!");
    }
}
