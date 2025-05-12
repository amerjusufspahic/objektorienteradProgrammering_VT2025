package lab3;
import lab3.accountManager.BankFunctions;
import lab3.exceptions.InvalidException;
import lab3.exceptions.UnknownException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		BankFunctions account = new BankFunctions();
		account.setBalance(1000); 
		List<Throwable> errorHistory = new ArrayList<>();

		boolean exit = false;

		while(!exit) {

			System.out.println("Welcome to Your Banking Application! \n\n" + "Choose transaction (deposit, withdraw, transfer, balance, exit):");
			String choiceInput = sc.nextLine();
			try {
				switch(choiceInput.toLowerCase()) {
				case "deposit":
					System.out.print("Enter amount to deposit");
					String amountInputDep = sc.nextLine().trim();
					int amountDep = Integer.parseInt(amountInputDep);
					account.deposit(amountDep);
					break;

				case "withdraw":
					System.out.print("Enter amount to withdraw");
					String amountInputWith = sc.nextLine().trim();
					int amountWith = Integer.parseInt(amountInputWith);
					account.deposit(amountWith);
					break;

				case "transfer":
					System.out.print("Enter amount to transfer");
					String amountInputTra = sc.nextLine().trim();
					int amountTra = Integer.parseInt(amountInputTra);
					account.deposit(amountTra);
					break;

				case "balance" :
					System.out.println("Current balance:" + account.getBalance());
					break;
				case "exit":
					exit = true;
					break;
				default:
					throw new UnknownException("Unknown transaction type: " + choiceInput);
				}
			} catch (InvalidException e) {
				System.out.println("Error: " + e.getMessage());
				errorHistory.add(e);
				e.printStackTrace();
				
			} catch (UnknownException e) {
				System.out.println("Error: " + e.getMessage());
				errorHistory.add(e);
				e.printStackTrace();
				
			}
			finally {
				System.out.println("...Logging attempt...");
			}

			System.out.println("\n>>> Stack Traces of All Caught Exceptions During This Session <<<\n");
			for (Throwable error : errorHistory) {
				System.out.println("----------------------------");
				error.printStackTrace(System.out);
			}

		}
		sc.close();
	}

}

