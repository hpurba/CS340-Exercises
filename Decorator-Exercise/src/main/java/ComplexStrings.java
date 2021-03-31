public class ComplexStrings extends StringDecorator {
  public ComplexStrings(StringSource newStringSource) {
    super(newStringSource);
  }

  public String next() {
    return tempSource.next() + "Captious, Malapropism, Tenebrous.";
  }
}
