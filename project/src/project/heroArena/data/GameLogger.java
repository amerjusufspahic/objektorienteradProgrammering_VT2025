package project.heroArena.data;

public class GameLogger {
	public static void log(String message) {
		System.out.println("[LOG] " + message);
	}

	public static void logCombat(String attacker, String defender, int damage) {
		log(attacker + " attacks " + defender + " for " + damage + " damage.");
	}

	public static void logVictory(String winner) {
		log(winner + " has won the battle!");
	}
}
