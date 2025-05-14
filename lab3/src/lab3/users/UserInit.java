package lab3.users;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class UserInit {
    public static void main(String[] args) {
    	
    	//skapar UserData objekt som hanterar anv filer och konto info
        UserData db = new UserData();
        //Läser in users.txt, validerar och sparar anv till users.ser och balances.txt
        db.processUserFile();
        System.out.println("Users processed and serialized.");

        // öppnar users-ser för att läsa in aööa sparade anv
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            @SuppressWarnings("unchecked")
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
