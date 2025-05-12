package lab3.users;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class UserInit {
    public static void main(String[] args) {
        UserData db = new UserData();
        db.processUserFile(); // Steg 1: bearbeta users.txt och skapa users.ser + balances.txt
        System.out.println("Users processed and serialized.");

        // Steg 2: läs users.ser och skriv ut användarnamn
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            Map<String, User> users = (Map<String, User>) ois.readObject();

            System.out.println("Registrerade användare:");
            for (String username : users.keySet()) {
                System.out.println("- " + username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
