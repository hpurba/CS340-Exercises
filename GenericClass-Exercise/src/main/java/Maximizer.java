import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Maximizer<T> implements Comparable {
  private T value;
  private List<T> list;

  public Maximizer() {
    this.list = new ArrayList<T>();
  }

  public void updateValue(T a) {
    list.add(a);
    sortList(list, (Comparator<T>) this.value);
  }

  public static <E> void sortList(List<E> list, Comparator<E> comparator) {
    Collections.sort(list, comparator);
  }

  public T getValue() {
    return list.get(list.size() - 1);
  }

  public T getMinValue() {
    return list.get(0);
  }

  public T getMaxValue() {
    return list.get(list.size() - 1);
  }

  public int compareTo(Object o) { return 0; }

  public T getList() {
    return (T) this.list;
  }
}