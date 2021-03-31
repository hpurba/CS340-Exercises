public class Adapter implements TableData {

  ContactManager contactManager;

  public Adapter(ContactManager newContactManager) {
    contactManager = newContactManager;
  }

  public int getColumnCount() {
    return 4;
  }

  public int getRowCount() {
    return contactManager.getContactCount();
  }

  public int getColumnSpacing() {
    return 1;
  }

  public int getRowSpacing() {
    return 1;
  }

  public char getHeaderUnderline() {
    return 1;
  }

  public String getColumnHeader(int col) {
    if(col == 0) {
      return "firstname";
    }
    else if (col == 1) {
      return "lastname";
    }
    else if (col == 2) {
      return "phone";
    }
    else {
      return "email";
    }
  }

  public int getColumnWidth(int col) {
    return 10;
  }

  public Justification getColumnJustification(int col) {
    return Justification.Center;
  }

  public String getCellValue(int row, int col) {
    if(col == 0){
      return contactManager.getContact(row).getFirstName();
    }
    else if (col == 1) {
      return contactManager.getContact(row).getLastName();
    }
    else if (col == 2) {
      return contactManager.getContact(row).getPhone();
    }
    else {
      return contactManager.getContact(row).getEmail();
    }
  }

  public void addContact(Contact contact) {
    contactManager.addContact(contact);
  }
}
