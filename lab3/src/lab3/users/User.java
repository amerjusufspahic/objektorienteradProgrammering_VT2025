package lab3.users;
import java.io.Serializable;


public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    
    public User(String username, String hashedPassword, String firstName,
            String lastName, String address, String phoneNumber) {
    this.username = username;
    this.hashedPassword = hashedPassword;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
}
    
    public String getUsername() { return username; }
    public String getHashedPassword() { return hashedPassword; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
}
