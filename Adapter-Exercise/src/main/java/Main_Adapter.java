import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class Main_Adapter {

  public static void main(String[] args) {
    ContactManager contactManager = new ContactManager();

    Contact contact0 = new Contact("Hikaru", "Purba", "385-45-254", "hpurba@gmail.com");
    Contact contact1 = new Contact("Jim", "Johnson", "385-45841-84244", "Jj@gmail.com");
    Contact contact2 = new Contact("Ray", "A.", "385-45812-4154", "Rayaaayyyy@gmail.com");
    Contact contact3 = new Contact("Stephen", "Hawkins", "385-4825-4345", "Stephen.Hawkins@gmail.com");
    contactManager.addContact(contact0);
    contactManager.addContact(contact1);
    contactManager.addContact(contact2);
    contactManager.addContact(contact3);

    TableData tableData = new Adapter(contactManager);

    Writer writer = new PrintWriter(System.out);
    Table table = new Table(writer, tableData);

    try {
      table.display();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Flush the writer.
    try {
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
