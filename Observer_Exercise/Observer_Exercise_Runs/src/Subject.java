import java.util.ArrayList;

public abstract class Subject {
  ArrayList<Observer> observers = new ArrayList<Observer>();

  // referred in spec as attach
  public void register(Observer observer) {
    observers.add(observer);
  }

  // referred in spec as notify
  public void updateObservers() {
    for (Observer o : observers)
      o.update();
  }
}
