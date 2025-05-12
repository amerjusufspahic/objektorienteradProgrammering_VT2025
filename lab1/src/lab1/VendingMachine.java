package lab1;

import java.util.Scanner;

public class VendingMachine {
	private Beverage[] beverages;
	private int beverageCount;

	private void pause(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {

		}
	}


	public VendingMachine(Beverage[] beverages) {
		this.beverages = beverages;
		this.beverageCount = 0;
	}

	public Beverage[] getBeverages() {
		return beverages;
	}

	public void setBeverages(Beverage[] beverages) {
		this.beverages = beverages;
	}
	public int getBeverageCount() {
		return beverageCount;
	}
	public void setBeverageCount(int beverageCount) {
		this.beverageCount = beverageCount;
	}

	public void printMenu() {
		System.out.println("Beveragemenu");
		for(int i=0; i < beverages.length; i++) {
			Beverage beverage = beverages[i];
			System.out.println((i + 1) + ". " + beverage.getName() + " - " + beverage.getPrice() + " kr (In stock: " + beverage.getStock() + ")");
		}
	}

	public void serveBeverages(int index) {
		Beverage n = beverages[index];
		if(n.getStock()>0) {
			System.out.println("Preparing " + n.getName() + "...");
			n.decreaseStock();
			beverageCount++;
			pause(1500);
			System.out.println("You got a " + n.getName() +".");
			System.out.println("");
		}
		else {
			System.out.println("Sorry" + n.getName() + " is out of stock." );
		}


	}

	public void printSummary() {
		System.out.println("Summary");
		double totalCost = 0;

		for(int i=0; i<beverages.length; i++) {
			Beverage n = beverages[i];
			int timesSelected = n.getTimesSelected();
			double cost = n.getPrice() * timesSelected;
			totalCost += cost;

			System.out.println(n.getName() +" was chosen "+ timesSelected +" times.Total: " + cost + "kr\n");
		}

		System.out.println("Total number of beverages:" + beverageCount);
		System.out.println("Total sales:" + totalCost + "kr");
	}

	public void run() {
		Scanner input = new Scanner(System.in);
		boolean running = true;

		while (running==true) {
			printMenu();
			System.out.println("Choose a number, write 0 for receipt and exit");

			int choice = input.nextInt();



			if (choice == 0) {
				running = false;
			}
			else if(choice > 0 && choice <= beverages.length) {
				serveBeverages(choice-1);
			}
			else {
				System.out.println("Invalid choice, please try again");
			}


		}

		printSummary();
		input.close();
	}



}
