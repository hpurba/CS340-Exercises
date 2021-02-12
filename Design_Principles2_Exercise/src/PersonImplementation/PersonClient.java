package PersonImplementation;

import java.io.IOException;

public class PersonClient extends Person {

  public PersonClient(String last, String first, String middle) {super(last, first, middle);}

  public String client1() throws IOException {
    StringBuilder str = new StringBuilder(this.first + " ");
    if (this.middle != null) {
      str.append(this.middle + " ");
    }
    str.append(this.last);
    return str.toString();
  }

  public String client2() {
    StringBuilder str = new StringBuilder(this.last + " " + this.first);
    if (this.middle != null){
      str.append(" " + this.middle);
    }
    return str.toString();
  }

  public String client3() throws IOException {
    StringBuilder str = new StringBuilder(this.last + ", " + this.first);
    if (this.middle != null) {
      str.append(" " + this.middle);
    }
    return str.toString();
  }

  public String client4() {
    StringBuilder str = new StringBuilder(this.last + ", " + this.first);
    if (this.middle == null) {
      str.append("");
    }
    else {
      str.append(" " + this.middle);
    }
    return str.toString();
  }
}