public class DecorationPlacer implements iDecorationPlacer {

    public DecorationPlacer() {

    }

    public void placeDecorations(String Holiday) {
        System.out.println("You selected: " + Holiday);
        if (Holiday.equals("H")) {
            iHolidayFactory holidayDecorationFactory = new HalloweenFactory();
            System.out.println(holidayDecorationFactory.placeDecorations());
        } else {
            iHolidayFactory holidayDecorationFactory = new ChristmasFactory();
            System.out.println(holidayDecorationFactory.placeDecorations());
        }
    }
}
