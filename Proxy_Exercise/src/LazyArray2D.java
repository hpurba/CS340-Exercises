import java.io.IOException;

public class LazyArray2D implements Array2D {
    Array2D subject = null;
    String filename;

    public LazyArray2D(String filename) {
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
                subject = ConcreteArray2D.load(filename);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
