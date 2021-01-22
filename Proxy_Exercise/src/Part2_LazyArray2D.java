import java.io.IOException;

public class Part2_LazyArray2D implements Part2_Array2D {
    Part2_Array2D subject = null;
    String filename;

    public Part2_LazyArray2D(String filename) {
        System.out.println("Creating Lazy Array Object");
        this.filename = filename;
    }

    @Override
    public void set(int row, int col, int value) {
        loadSubjectIfNeeded();
        subject.set(row, col, value);
    }

    @Override
    public int get(int row, int col) {
        loadSubjectIfNeeded();
        return subject.get(row, col);
    }

    private void loadSubjectIfNeeded() {
        try {
            if (subject == null) {
                System.out.println("Retrieve the Array Object.");
                subject = new Part2_ConcreteArray2D(filename);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
