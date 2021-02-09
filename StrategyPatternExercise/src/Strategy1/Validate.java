package Strategy1;

public interface Validate {
  String validateText(int numChars);
}

class ShortOnly implements Validate {
  @Override
  public String validateText(int numChars) {
    if(numChars < 11) {
      System.out.println("Valid/n");
      return "Valid";
    }
    else {
      System.out.println("InValid/n");
      return "invalid";
    }
  }
}

class LongOnly implements Validate {
  @Override
  public String validateText(int numChars) {
    if(numChars >= 11) {
      System.out.println("Valid/n");
      return "Valid";
    }
    else {
      System.out.println("InValid/n");
      return "invalid";
    }
  }
}