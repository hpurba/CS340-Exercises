package Strategy4;

public class Main {
  public static void main(String[] args){
    AI myFirstAI = new AI(new OffensiveStrategy(), new DefensiveStrategy(), new IdleStrategy());
    System.out.println("Let the games begin against this amazing AI. Fight!");
    myFirstAI.fight();

    System.out.println("End of Game.\nGame Restarted! Fight!");
    myFirstAI.fight();
    myFirstAI.fight();
    System.out.println("AI has lost!");
  }
}