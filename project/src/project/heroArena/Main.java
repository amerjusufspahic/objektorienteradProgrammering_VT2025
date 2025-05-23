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

        
        List<Item> items = FileManager.loadItems("items.txt");

        List<Weapon> weapons = new ArrayList<>();
        List<Armor> armors = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof Weapon) {
                weapons.add((Weapon) item);
            } else if (item instanceof Armor) {
                armors.add((Armor) item);
            }
        }
        
        if (weapons.size() >= 2 && armors.size() >= 2) {
        	hero.addItem(weapons.get(0));
            hero.equipItem(hero.getInventory().size() - 1);
            hero.addItem(armors.get(0));
            hero.equipItem(hero.getInventory().size() - 1);

            monster.addItem(weapons.get(1));
            monster.addItem(armors.get(1));
            monster.equipItem(0);
            monster.equipItem(1);
            
        } 
         System.out.println("");
        GameLogger.log("A wild " + monster.getName() + " appears! The battle begins ");

        while (hero.isAlive() && monster.isAlive()) {
            System.out.println("\n=== " + hero.getName() + "'s turn ===");
            System.out.println("HP: " + hero.getHealth());
            System.out.println("1. Attack");
            System.out.println("2. View Inventory");
            System.out.println("3. Equip Item");

            int choice = InputHelper.readInt("Choose action: ", 1, 3);
            System.out.println("");

            switch (choice) {
                case 1 -> {hero.takeTurn(monster);
                System.out.println(monster.getName() + " HP: " + monster.getHealth());
                System.out.println("\n=== " + monster.getName() + "'s Turn ===");
                monster.takeTurn(hero);
                
                
                }
                case 2 -> {
                    List<Item> inv = hero.getInventory();
                    System.out.println("Inventory:");
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
                    GameLogger.logEquip(hero.getName(), inv.get(itemIndex).getName());
                    System.out.println("");
                }
            }

            if (!monster.isAlive()) break;

           
        }

        if (hero.isAlive()) {
            GameLogger.logVictory(hero.getName());
        } else {
            GameLogger.logVictory(monster.getName());
        }
    }
}