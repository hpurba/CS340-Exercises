package Strategy4;

import Strategy1.Validate;

public interface IStrategy {
  public void fight();
}

class DefensiveStrategy implements IStrategy {
  @Override
  public void fight() {
    System.out.println("Using Shield");
    System.out.println("Did a block");
  }
}

class OffensiveStrategy implements IStrategy {
  @Override
  public void fight() {
    System.out.println("Using Sword");
    System.out.println("Slashed Opponent in Two!");
  }
}

class IdleStrategy implements IStrategy {
  @Override
  public void fight() {
    System.out.println("Eating a delicious sandwich and look at self in mirror.");
    System.out.println("Checking Robinhood accound balance.");
    System.out.println("Cashed out on GME at $354 a share!");
  }
}