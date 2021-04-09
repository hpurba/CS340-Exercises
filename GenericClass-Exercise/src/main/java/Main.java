public class Main {
  public static void main(String[] args) {
    // Spec Sample Code
    Maximizer<String> strMaximizer = new Maximizer<String>();
    strMaximizer.updateValue("a");
    strMaximizer.updateValue("z");
    strMaximizer.updateValue("m");
    String maxStr = strMaximizer.getValue();
    System.out.println(maxStr);

    Maximizer<Integer> intMaximizer = new Maximizer<Integer>();
    intMaximizer.updateValue(-22);
    intMaximizer.updateValue(10000);
    intMaximizer.updateValue(33);
    Integer maxInt = intMaximizer.getValue();
    System.out.println(maxInt);

    // Another implementation (my own)
    String[] strArr = new String[] { "y", "x", "b", "n", "h" };
    Stats<String> strStats = Algorithms.calcStats(strArr);
    System.out.println(String.format("min: %s, max: %s", strStats.min, strStats.max));

    Integer[] intArr = new Integer[] { 100000, 4591, 26483, -22, 0 };
    Stats<Integer> intStats = Algorithms.calcStats(intArr);
    System.out.println(String.format("min: %d, max: %d", intStats.min, intStats.max));
  }
}