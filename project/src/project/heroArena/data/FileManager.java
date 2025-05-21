package project.heroArena.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.heroArena.items.Armor;
import project.heroArena.items.Item;
import project.heroArena.items.Weapon;

public class FileManager {
	public static List <Item> loadItems(String filename){
		List<Item> items = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String line;
			while((line = reader. readLine()) != null){
				String[] parts = line.split(";");
				if(parts[0].equalsIgnoreCase("Weapon")) {
					items.add(new Weapon(parts[1], Integer.parseInt(parts[2])));
				}else if(parts[0].equalsIgnoreCase("Armor")) {
					items.add(new Armor(parts[1], Integer.parseInt(parts[2])));
				}
			}
		} catch(IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		return items;
	}
}
