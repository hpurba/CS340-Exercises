public class DecorationPlacer {

    // FIXME use dependency inversion to remove these hard-coded dependencies
    private HalloweenTableclothPatternProvider tableclothPattern = new HalloweenTableclothPatternProvider();
    private HalloweenWallHangingProvider wallHanging = new HalloweenWallHangingProvider();
    private HalloweenYardOrnamentProvider yardOrnament = new HalloweenYardOrnamentProvider();

    public DecorationPlacer() {

    }

    public String placeDecorations() {
        return "Everything was ready for the party. The " + yardOrnament.getOrnament()
                + " was in front of the house, the " + wallHanging.getHanging()
                + " was hanging on the wall, and the tablecloth with " + tableclothPattern.getTablecloth()
                + " was spread over the table.";
    }
}
