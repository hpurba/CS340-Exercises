package Strategy2;

public class TraditionalApplication extends EmailClient {
  public TraditionalApplication() {
    sendMessageLoginType = new UserConfigurationFile();
  }
}