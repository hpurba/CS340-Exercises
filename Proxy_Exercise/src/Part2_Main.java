public class Part2_Main {
    public static void main(String[] args) {
        Part2_ConcreteArray2D array = new Part2_ConcreteArray2D(100, 100);
        array.set(12, 10, 50);
        System.out.println(array.get(12, 10));

        try {
            array.save("Data");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            Part2_LazyArray2D arrayLazy = new Part2_LazyArray2D("Data");
            System.out.println(arrayLazy.get(12, 10));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
