public class DecorationPlacer {

    // FIXME use dependency inversion to remove these hard-coded dependencies
    private iHalloweenTableclothPatternProvider tableclothPattern = new HalloweenTableclothPatternProvider();
    private iHalloweenWallHangingProvider wallHanging = new HalloweenWallHangingProvider();
    private iHalloweenYardOrnamentProvider yardOrnament = new HalloweenYardOrnamentProvider();

    public DecorationPlacer() {

    }

    public String placeDecorations() {
        return "Everything was ready for the party. The " + yardOrnament.toString()
                + " was in front of the house, the " + wallHanging.toString()
                + " was hanging on the wall, and the tablecloth with " + tableclothPattern.toString()
                + " was spread over the table.";
    }
}
