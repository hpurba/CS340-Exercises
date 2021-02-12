package Strategy2;

public class Main {
  public static void main(String[] args) {
    EmailClient myConsoleApp = new EmailClient(new ConsolePrompt());
    myConsoleApp.promptLogin();

    EmailClient myGraphicalApp = new EmailClient(new GraphicalDisplay());
    myGraphicalApp.promptLogin();

    EmailClient myOldStyleApp = new EmailClient(new UserConfigurationFile());
    myOldStyleApp.promptLogin();
  }
}