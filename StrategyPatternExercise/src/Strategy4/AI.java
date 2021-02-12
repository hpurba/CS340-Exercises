package Strategy4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI {
  // List of strategies that the AI can use
  public List<IStrategy> strategiesList = new ArrayList<>();

  public AI(IStrategy strategy1, IStrategy strategy2, IStrategy strategy3) {
    this.strategiesList.add(strategy1);
    this.strategiesList.add(strategy2);
    this.strategiesList.add(strategy3);
  }

  public void addStrategy(IStrategy strategy){
    this.strategiesList.add(strategy);
  }

  public void fight() {
    int i = 0;
    Random randNum = new Random();
    while (i++ < 4) {
      strategiesList.get(randNum.nextInt(strategiesList.size())).fight();
    }
  }
}