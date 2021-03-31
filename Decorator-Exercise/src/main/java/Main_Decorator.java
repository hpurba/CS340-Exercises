public class Main_Decorator {
  public static void main(String[] args){
    StringSource awesomeString = new ComplexStrings(new CoolnessStrings(new PlainString()));
    System.out.println("Your Super Awesome String: \n" + awesomeString.next());
  }
}
