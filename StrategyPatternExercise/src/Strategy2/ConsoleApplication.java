package Strategy2;

public class ConsoleApplication extends EmailClient {
  public ConsoleApplication() {
    sendMessageLoginType = new ConsolePrompt();
  }
}