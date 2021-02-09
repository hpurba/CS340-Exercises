package Strategy2;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Main {
  public static void main(String[] args) {
    EmailClient myConsoleApp = new ConsoleApplication();
    myConsoleApp.promptLogin();

    EmailClient myGraphicalApp = new GraphicalApplication();
    myGraphicalApp.promptLogin();

    EmailClient myOldStyleApp = new TraditionalApplication();
    myOldStyleApp.promptLogin();
  }
}