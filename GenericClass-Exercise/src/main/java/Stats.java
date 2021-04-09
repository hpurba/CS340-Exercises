public class Stats<T> {
  public T min;
  public T max;

  public Stats(T m, T mx) {
    this.min = m;
    this.max = mx;
  }
}