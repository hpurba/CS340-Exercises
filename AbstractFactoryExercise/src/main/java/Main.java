import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        DecorationPlacer decorationPlacer = new DecorationPlacer();
//        System.out.println(decorationPlacer.placeDecorations());

        DecorationPlacer decorationPlacer = new DecorationPlacer();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Which holiday is coming up? (H: Holloween / C: Christmas)");
        if (userInput.hasNextLine()) {
            String typeOfHoliday = userInput.nextLine();
            decorationPlacer.placeDecorations(typeOfHoliday);
        }
    }
}
