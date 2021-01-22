package Part2;

public class Main {
    public static void main(String[] args) {
        ConcreteArray2D array = new ConcreteArray2D(100, 100);
        array.set(12, 10, 50);
        System.out.println(array.get(12, 10));

        try {
            array.save("Data");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            LazyArray2D arrayLazy = new LazyArray2D("Data");
            System.out.println(arrayLazy.get(12, 10));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
