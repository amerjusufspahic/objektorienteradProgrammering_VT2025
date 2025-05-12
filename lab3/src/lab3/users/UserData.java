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
            scanner = new Scanner(new File(USER_INPUT_FILE));
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

                if (!validate(username, password, firstName, lastName, address, phone, balanceStr)) continue;

                String hashed = hashPassword(password);
                User user = new User(username, hashed, firstName, lastName, address, phone);
                users.put(username, user);
                balances.put(username, Integer.parseInt(balanceStr));
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
                oos.writeObject(users);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BALANCES_FILE))) {
                for (var entry : balances.entrySet()) {
                    bw.write(entry.getKey() + "," + entry.getValue());
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Släpp scanner-resursen (om inte redan stängd)
            if (scanner != null) {
                scanner.close();
            }

            // Försök ta bort users.txt säkert
            try {
                Thread.sleep(100); // säkerställ att scanner har släppt filen
                File file = new File(USER_INPUT_FILE);
                boolean deleted = file.delete();
                System.out.println("users.txt deleted? " + deleted);
            } catch (InterruptedException e) {
                System.err.println("Tråden avbröts vid väntan på filradering.");
                e.printStackTrace();
            }
        }
    }


    private boolean validate(String username, String password, String firstName, String lastName,
                             String address, String phone, String balance) {
        return username.matches("^[A-Za-z][A-Za-z0-9_]{4,11}$") &&
               password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#]).{8,}$") &&
               firstName.matches("^[A-Za-z]+$") &&
               lastName.matches("^[A-Za-z]+$") &&
               address.matches("^[\\w\\s.,-]{5,50}$") &&
               phone.matches("^\\+46\\d{7,9}$") &&
               balance.matches("^\\d+$");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            users = new HashMap<>();
        }
    }

    private void loadBalances() {
        balances = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BALANCES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                balances.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException ignored) {}
    }

    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        if (user == null) return false;
        return user.getHashedPassword().equals(hashPassword(password));
    }

    public int getBalance(String username) {
        return balances.getOrDefault(username, 0);
    }

    public void updateBalance(String username, int newBalance) {
        balances.put(username, newBalance);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCES_FILE))) {
            for (var entry : balances.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logTransaction(String username, String type, int amount) {
        try (Formatter formatter = new Formatter(new FileWriter("history_" + username + ".log", true))) {
            formatter.format("[%s] %s %d%n", new Date(), type.toUpperCase(), amount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}