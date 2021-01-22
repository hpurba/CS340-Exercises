import java.io.*;

public class Part2_ConcreteArray2D implements Part2_Array2D, Serializable {
  int[][] data;
  int width;
  int height;

  public Part2_ConcreteArray2D(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new int[width][height];
  }

  public Part2_ConcreteArray2D(String filename) throws IOException, ClassNotFoundException {
    this.data = load(filename);
  }

  public void save(String filename) throws IOException {
    FileOutputStream fs = new FileOutputStream(filename);
    ObjectOutputStream os = new ObjectOutputStream(fs);
    os.writeObject(this.data);
  }

  public int[][] load(String filename) throws IOException, ClassNotFoundException {
    FileInputStream fs = new FileInputStream(filename);
    ObjectInputStream os = new ObjectInputStream(fs);
    return (int[][]) os.readObject();
  }

  @Override
  public void set(int row, int col, int value) {
    data[row][col] = value;
  }

  @Override
  public int get(int row, int col) {
    return data[row][col];
  }

//  private int index(int row, int col) {
//    return ((width * row) + col);
//  }
}
