package lab1;

import java.util.Random;

public class Main {
	public static void main(String[] args) {

		String[] names = {"Nocco", "Celsius", "Sprite", "GAAM", "Water"};
		double[] prices = {25.0, 20.9, 11.5, 10.0, 5.0};

		Beverage[] beverages = new Beverage[names.length];
		Random rand = new Random();

		for (int i = 0; i < names.length; i++) {
			int randomStock = rand.nextInt(20) + 1; 
			beverages[i] = new Beverage(names[i], prices[i], randomStock, 0);
		}

		VendingMachine vm = new VendingMachine(beverages);
		vm.run();
	}
}

