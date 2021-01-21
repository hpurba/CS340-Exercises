import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ConcreteArray2D implements Array2D, Serializable {
  int[] data;
  int width;
  int height;

  public ConcreteArray2D(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new int[width * height];
  }

  public void save(String filename) throws IOException {
    FileOutputStream fs = new FileOutputStream(filename);
    ObjectOutputStream os = new ObjectOutputStream(fs);
    os.writeObject(this);
  }

  public static Array2D load(String filename) throws IOException, ClassNotFoundException {
    FileOutputStream fs = new FileOutputStream(filename);
    ObjectOutputStream os = new ObjectOutputStream(fs);
    return (ConcreteArray2D)os.readObject();
  }

  @Override
  public void set(int row, int col, int value) {
    data[index(row,col)] = value;
  }

  @Override
  public int get(int row, int col) {
    return data[index(row, col)];
  }

  private int index(int row, int col) {
    return ((width * row) + col);
  }
}
