package Strategy1;

public class Main {
  public static void main(String[] args) {
    TextBox yellowBoxy = new TextBox(new ShortOnly());

    yellowBoxy.setText("Hello");                          // Valid Text
    yellowBoxy.validate();
    yellowBoxy.setText("ThisTextIsWaaaaaayyyyyToooLong"); // Invalid Text
    yellowBoxy.validate();

    // Set to long only
    yellowBoxy.setValidator(new LongOnly());
    yellowBoxy.setText("Short");                          // InValid Text
    yellowBoxy.validate();
  }
}