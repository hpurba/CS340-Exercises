package Strategy1;

public class Main {
  public static void main(String[] args) {
    TextBox yellowBoxy = new TextBox(new ShortOnly());

    yellowBoxy.setText("Hello");  // Valid Text
    yellowBoxy.validate();
    yellowBoxy.setText("ThisTextIsWaaaaaayyyyyToooLong"); // Invalid Text
    yellowBoxy.validate();

    yellowBoxy.setValidator(new LongOnly());
    yellowBoxy.setText("ThisTextIsWaaaaaayyyyyToooLong"); // Invalid Text
    yellowBoxy.validate();



//
//    TextBox blueBoxy = new BlueBox();
//    blueBoxy.setText("Hello");  // Valid Text
//    blueBoxy.validate();
  }
}