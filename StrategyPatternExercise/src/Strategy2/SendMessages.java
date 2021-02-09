package Strategy2;

public interface SendMessages {
  void validateMethod();
}

class ConsolePrompt implements SendMessages {
  @Override
  public void validateMethod() {
    System.out.println("Enter username and password in console!\n (Console pops up)\n\n");
  }
}

class GraphicalDisplay implements SendMessages {
  @Override
  public void validateMethod() {
    System.out.println("Enter Username and password in textboxes!\n (Graphical Interface pops up with boxes)\n\n");
  }
}

class UserConfigurationFile implements SendMessages {
  @Override
  public void validateMethod() {
    System.out.println("Enter Configuration File!\n (Pops up a file explore to select the file with credentials.)\n\n");
  }
}
