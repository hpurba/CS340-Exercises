import Christmas.*;

public class ChristmasFactory implements iHolidayFactory {

  private iChristmasTableclothPatternProvider tableclothPattern = new ChristmasTableclothPatternProvider();
  private iChristmasWallHangingProvider wallHanging = new ChristmasWallHangingProvider();
  private iChristmasYardOrnamentProvider yardOrnament = new ChristmasYardOrnamentProvider();

  public String placeDecorations() {
    return "Everything was ready for the party. The " + yardOrnament.toString()
      + " was in front of the house, the " + wallHanging.toString()
      + " was hanging on the wall, and the tablecloth with " + tableclothPattern.toString()
      + " was spread over the table.";
  }
}