

public class Contact {

	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	public Contact() {
		this("", "", "", "");
	}
	
	public Contact(String firstName, String lastName, String phone, String email) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setEmail(email);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String value) {
		firstName = value;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String value) {
		lastName = value;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String value) {
		phone = value;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String value) {
		email = value;
	}
	
}
