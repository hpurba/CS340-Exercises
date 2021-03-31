
import java.util.*;


public class ContactManager {

	private List<Contact> contacts;
	
	public ContactManager() {
		contacts = new ArrayList<Contact>();
	}
	
	public int getContactCount() {
		return contacts.size();
	}
	
	public Contact getContact(int index) {
		return contacts.get(index);
	}
	
	public void setContact(int index, Contact value) {
		contacts.set(index, value);
	}
	
	public void addContact(Contact value) {
		contacts.add(value);
	}
	
}
