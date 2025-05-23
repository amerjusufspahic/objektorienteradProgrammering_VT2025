package project.heroArena.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameLogger {
    private static final String LOG_FILE = "game_log.txt";

    public static void log(String message) {
        String Message = message;
        System.out.println(Message); 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(Message);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(" Failed to write log: " + e.getMessage());
        }
    }

    public static void logCombat(String attacker, String defender, int damage) {
        log(attacker + " attacks " + defender + " for " + damage + " damage.");
    }

    public static void logVictory(String winner) {
        log(winner + " has won the battle!");
    }

    public static void logEquip(String character, String item) {
        log(character + " equipped " + item);
    }
}