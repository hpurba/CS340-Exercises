public class Algorithms {
  public static < E > Stats calcStats( E[] inputArray ) {
    Maximizer<E> myMaximizer = new Maximizer<E>();
    for (int i = 0; i < inputArray.length; i++) {
      myMaximizer.updateValue(inputArray[i]);
    }
    Stats<E> myStats = new Stats<E>(myMaximizer.getMinValue(), myMaximizer.getMaxValue());
    return myStats;
  }
}