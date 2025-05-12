package lab3.accountManager;

import lab3.exceptions.InvalidException;
import lab3.exceptions.UnknownException;

public class BankFunctions {
	private int balance;

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void deposit(int amount) throws InvalidException {
		if(amount <= 0) {
			throw new InvalidException("Deposit amount must be positive.");
		}

		balance +=amount;

	}

	public void withdraw(int amount) throws InvalidException {
		if(amount<=0) {
			throw new InvalidException("Deposit must be positive,");
		}

		if(amount>balance) {
			throw new InvalidException("Insufficent balance");
		}

		balance -=amount;
	}

	public void transfer(int amount) throws InvalidException {
		if(amount<=0) {
			throw new InvalidException("Deposit must be positive,");
		}

		if(amount>balance) {
			throw new InvalidException("Insufficent balance");
		}

		balance -=amount;
	}

	public void processTransaction(String type, int amount)throws InvalidException, UnknownException {
		switch (type.toLowerCase()) {
		case "deposit":
			deposit(amount);
			break;
		case "withdraw":
			withdraw(amount);
			break;
		case "transfer":
			transfer(amount);
			break;
		default:
			throw new UnknownException("Invalid transaction type: " + type);	
		}
	}
}

