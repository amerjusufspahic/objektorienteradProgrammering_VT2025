package project.heroArena.utils;
import java.util.Scanner;


public class InputHelper {
	private static Scanner scanner = new Scanner(System.in);

	public static int readInt(String prompt, int min, int max) {
		int input;
		while (true) {
			System.out.print(prompt);
			try {
				input = Integer.parseInt(scanner.nextLine());
				if (input >= min && input <= max) {
					return input;
				}
			} catch (NumberFormatException ignored) {}
			System.out.println("Invalid input. Try again.");
		}

	}

	public static String readLine(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}
}