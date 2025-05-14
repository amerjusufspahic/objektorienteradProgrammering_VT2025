package lab3.users;



import java.io.*;
import java.security.MessageDigest;
import java.util.*;

import java.util.Base64;

public class UserData {

	private static final String USER_INPUT_FILE = "users.txt";
	private static final String USERS_FILE = "users.ser";
	private static final String BALANCES_FILE = "balances.txt";

	private Map<String, User> users = new HashMap<>();
	private Map<String, Integer> balances = new HashMap<>();

	public UserData() {
		loadUsers();
		loadBalances();
	}

	public void processUserFile() {
		Scanner scanner = null;

		try {
			//försöker läsa in filen
			scanner = new Scanner(new File(USER_INPUT_FILE));


			//delar upp varje rad efter ","
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine().trim();
				String[] parts = line.split(",");
				if (parts.length != 7) continue;

				String username = parts[0];
				String password = parts[1];
				String firstName = parts[2];
				String lastName = parts[3];
				String address = parts[4];
				String phone = parts[5];
				String balanceStr = parts[6];

				//validerar innehållet med funktionen
				if (!validate(username, password, firstName, lastName, address, phone, balanceStr)) continue;

				//hashar lösenordet till säker sträng
				String hashed = hashPassword(password);

				//skapar User objektet
				User user = new User(username, hashed, firstName, lastName, address, phone);
				users.put(username, user);
				balances.put(username, Integer.parseInt(balanceStr));
			}
			//försöker skapa users.ser och sparar användarna
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
				oos.writeObject(users);
			}
			// försöker skapa balances.txt med username och balance
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(BALANCES_FILE))) {
				for (var entry : balances.entrySet()) {
					bw.write(entry.getKey() + "," + entry.getValue());
					bw.newLine();
				}
			}

		} 
		// catchar alla fel och skriver felmeddelande
		catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (scanner != null) {
				scanner.close();
			}

			try {
				// försöker vänta, radera filen och skriva ut om lyckats
				Thread.sleep(100); 
				File file = new File(USER_INPUT_FILE);
				boolean deleted = file.delete();
				System.out.println("users.txt deleted? " + deleted);
			}
			// gör ingenting men är med pga kompileringen
			catch (InterruptedException n) {

			}

		}
	}


	private boolean validate(String username, String password, String firstName, String lastName,
			String address, String phone, String balance) {
		return username.matches("^[A-Za-z][A-Za-z0-9_]{4,11}$") && //börjar med bokstav, 5-12 tecken
				password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#]).{8,}$") && // minst 1 bokstav, 1 siffra, ett specialtecken och minst 8 tecken tot
				firstName.matches("^[A-Za-z]+$") && // bara bokstäver
				lastName.matches("^[A-Za-z]+$") && // bara bokstäver
				address.matches("^[\\w\\s.,-]{5,50}$") && // 5-50 tecken bokstäver, siffror .,-
				phone.matches("^\\+46\\d{7,9}$") && // börjar med +46 och 7-9 siffror
				balance.matches("^\\d+$"); // positivt heltal
	}

	private String hashPassword(String password) {
		try {
			//hashar lösenordet 
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes());
			//gör det till Base64 så att det blir till text
			return Base64.getEncoder().encodeToString(hash);
		} 

		// fångar fel och och kastar nytt som krashar då inget fångar det
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@SuppressWarnings("unchecked")
	private void loadUsers() {
		//försöker läsa in user.ser och lägga till users i Map<String, User>>
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
			//returnerar objekt
			users = (Map<String, User>) ois.readObject();	
		} 
		//fsngar undantag om fel och skapar tom Hashmap
		catch (Exception e) {
			users = new HashMap<>();
		}
	}

	private void loadBalances() {
		balances = new HashMap<>();
		//försöker läsa balances.txt med anv namn och saldo
		try (BufferedReader reader = new BufferedReader(new FileReader(BALANCES_FILE))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// delar upp i två delar 
				String[] parts = line.split(",");
				//lägger in i Hashmapen balances
				balances.put(parts[0], Integer.parseInt(parts[1]));
			}
		} 
		// ingenting händer
		catch (IOException ignored) {

		}
	}

	public boolean authenticate(String username, String password) {
		//hämtar anv men inmatat anv namn
		User user = users.get(username);
		if (user == null) return false;
		//jämför lösenordet med det som finns sparat och returnerar true om sant
		return user.getHashedPassword().equals(hashPassword(password));
	}

	public int getBalance(String username) {
	//returnerar saldo för anv, annars 0
	return balances.getOrDefault(username, 0);
}

public void updateBalance(String username, int newBalance) {
	//uppdaterar saldot för anv
	balances.put(username, newBalance);
	try (	//skriver över i filen 
			BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCES_FILE))) {
		//går igenom varje rad och skriver varje username och saldo
		for (var entry : balances.entrySet()) {
			writer.write(entry.getKey() + "," + entry.getValue());
			writer.newLine();
		}

	} 
	// fångar fel
	catch (IOException e) {
		e.printStackTrace();
	}
}

public void logTransaction(String username, String type, int amount) {
	try (	//skapar fil med med namn och lägger till längst ner
			Formatter formatter = new Formatter(new FileWriter("history_" + username + ".log", true))) {
		//loggar datum, vad den gör och belopp
		formatter.format("[%s] %s %d%n", new Date(), type.toUpperCase(), amount);
	}
	//fångar fel
	catch (IOException e) {
		e.printStackTrace();
	}
}
}