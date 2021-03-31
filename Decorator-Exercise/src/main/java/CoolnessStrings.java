public class CoolnessStrings extends StringDecorator {
  public CoolnessStrings(StringSource newStringSource) {
    super(newStringSource);
  }

  public String next() {
    return tempSource.next() + "Sick, Dope, Superduper, Rad, ";
  }
}
