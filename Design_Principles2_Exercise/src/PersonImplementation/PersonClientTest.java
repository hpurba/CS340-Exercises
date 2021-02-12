package PersonImplementation;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonClientTest {// extends TestCase {
  PersonClient bobSmith;
  PersonClient jennyJJones;

  @BeforeEach
  public void setUp() throws Exception {
    PersonClient bobSmith = new PersonClient("Smith", "Bob", null);
    PersonClient jennyJJones = new PersonClient("Jones", "Jenny", "J");
  }

  @AfterEach
  public void cleanUp() throws Exception {
    PersonClient bobSmith = null;
    PersonClient jennyJJones = null;
  }

  // Client 1
  @Test
  public void testClient1() throws IOException {
    PersonClient bobSmith = new PersonClient("Smith", "Bob", null);
    PersonClient jennyJJones = new PersonClient("Jones", "Jenny", "J");

    assertEquals("Bob Smith", bobSmith.client1());
    assertEquals("Jenny J Jones", jennyJJones.client1());
  }

  // Client 2
  @Test
  public void testClient2() throws IOException {
    PersonClient bobSmith = new PersonClient("Smith", "Bob", null);
    PersonClient jennyJJones = new PersonClient("Jones", "Jenny", "J");

    assertEquals("Smith, Bob", bobSmith.client2());
    assertEquals("Jones, Jenny J", jennyJJones.client2());
  }

  // Client 3
  @Test
  public void testClient3() throws IOException {
    PersonClient bobSmith = new PersonClient("Smith", "Bob", null);
    PersonClient jennyJJones = new PersonClient("Jones", "Jenny", "J");

    assertEquals("Smith, Bob", bobSmith.client3());
    assertEquals("Jones, Jenny J", jennyJJones.client3());
  }

  // Client 4
  @Test
  public void testClient4() throws IOException {
    PersonClient bobSmith = new PersonClient("Smith", "Bob", null);
    PersonClient jennyJJones = new PersonClient("Jones", "Jenny", "J");

    assertEquals("Smith, Bob", bobSmith.client4());
    assertEquals("Jones, Jenny J", jennyJJones.client4());
  }
}