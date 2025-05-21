package project.heroArena;

import project.heroArena.characters.*;
import project.heroArena.items.*;
import project.heroArena.utils.*;
import project.heroArena.data.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hero Arena === ");
        String heroName = InputHelper.readLine("Enter your hero's name: ");
        Hero hero = new Hero(heroName);
        Monster monster = new Monster("Goblin");

        // Ladda in items från fil
        List<Item> items = FileManager.loadItems("items.txt");

        // Ge hjälten några föremål
        for (int i = 0; i < Math.min(4, items.size()); i++) {
            hero.addItem(items.get(i));
        }

        // Startutrustning
        hero.equipItem(0);
        hero.equipItem(1);

        monster.equipWeapon("Ice Blade");
        monster.equipArmor("Magic Cloak");

        // Spelloop
        while (hero.isAlive() && monster.isAlive()) {
            System.out.println("\n=== " + hero.getName() + "'s turn ===");
            System.out.println("1. Attack");
            System.out.println("2. View Inventory");
            System.out.println("3. Equip Item");

            int choice = InputHelper.readInt("Choose action: ", 1, 3);

            switch (choice) {
                case 1 -> hero.takeTurn(monster);
                case 2 -> {
                    List<Item> inv = hero.getInventory();
                    for (int i = 0; i < inv.size(); i++) {
                        System.out.println(i + ": " + inv.get(i).getType() + " - " + inv.get(i).getName());
                    }
                }
                case 3 -> {
                    List<Item> inv = hero.getInventory();
                    for (int i = 0; i < inv.size(); i++) {
                        System.out.println(i + ": " + inv.get(i).getType() + " - " + inv.get(i).getName());
                    }
                    int itemIndex = InputHelper.readInt("Item number to equip: ", 0, inv.size() - 1);
                    hero.equipItem(itemIndex);
                }
            }

            if (!monster.isAlive()) break;

            System.out.println("\n=== " + monster.getName() + "'s Turn ===");
            monster.takeTurn(hero);
        }

        // Spel slut
        if (hero.isAlive()) {
            GameLogger.logVictory(hero.getName());
        } else {
            GameLogger.logVictory(monster.getName());
        }
    }
}