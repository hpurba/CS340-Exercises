package Strategy1;

/**
 * 1. A graphical user interface (GUI) library provides a TextBox class that lets the user enter a text value.
 *  The visual appearance of a TextBox changes depending on whether the text currently in the box is valid or invalid.
 *  What constitutes valid text is highly application-specific.
 */
public class TextBox {
  private String text;
  private int numChars;

  public Validate validityType;

  public void setText(String text) {
    this.text = text;
    this.numChars = text.length();
  }

  public int getNumChars() {
    return numChars;
  }

  public TextBox(Validate validityType){
    this.validityType = validityType;
  }

  public void setValidator(Validate validityType){
    this.validityType = validityType;
  }

  public String validate() {
    return validityType.validateText(numChars);
  }
}