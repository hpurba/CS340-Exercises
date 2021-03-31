
abstract class StringDecorator implements StringSource {

  protected StringSource tempSource;

  public StringDecorator(StringSource newStringSource) {
    tempSource = newStringSource;
  }

  public String next() {
    return tempSource.next();
  }

}
