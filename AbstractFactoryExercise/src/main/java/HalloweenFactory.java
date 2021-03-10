import Halloween.*;

public class HalloweenFactory implements iHolidayFactory {

  private iHalloweenTableclothPatternProvider tableclothPattern = new HalloweenTableclothPatternProvider();
  private iHalloweenWallHangingProvider wallHanging = new HalloweenWallHangingProvider();
  private iHalloweenYardOrnamentProvider yardOrnament = new HalloweenYardOrnamentProvider();

  public String placeDecorations() {
    return "Everything was ready for the party. The " + yardOrnament.toString()
      + " was in front of the house, the " + wallHanging.toString()
      + " was hanging on the wall, and the tablecloth with " + tableclothPattern.toString()
      + " was spread over the table.";
  }
}