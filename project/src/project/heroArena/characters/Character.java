package project.heroArena.characters;
import java.util.ArrayList;

import project.heroArena.items.Armor;
import project.heroArena.items.Item;
import project.heroArena.items.Weapon;

public abstract class Character {
	private String name;
	private int health;
	private String weapon;
	private String armor;
	private Weapon equippedWeapon;
	private Armor equippedArmor;
	
	private ArrayList<Item> inventory = new ArrayList<>();
	
	public void addItem(Item item) {
		inventory.add(item);
		System.out.println(name + " picked upp: " + item.getName());
	}
	
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	
	public void equipItem(int index) {
		if (index < 0 || index >= inventory.size()) {
			System.out.println("Invalid item number.");
			return;
		}
		
		Item item = inventory.get(index) ;
		if(item instanceof Weapon) {
			equippedWeapon = (Weapon) item;
			System.out.println(name + " equipped weapon: " + item.getName());
			
		}else if (item instanceof Armor) {
			equippedArmor = (Armor) item;
			System.out.println(name + " equipped armor: " + item.getName());
		}
	}
	
	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}
	public Armor getEquippedArmor() {
		return equippedArmor;
	}
	
	public Character (String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	public void equipWeapon(String weapon) {
		this.weapon	= weapon;
	}
	
	public void equipArmor(String armor) {
		this.armor = armor;
	}
	
	public void receiveDamage(int damage) {
		this.health -= Math.max(damage, 0);
		if(this.health <0) {
			this.health = 0;
		}
	}
	
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public void setName() {
		this.name = name;
		
	}
	public void setHealth() {
		this.health = health;
	}
	
	public void setArmor() {
		this.armor = armor;
	}
	
	public void setWeapon() {
		this.weapon = weapon;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public String getArmor() {
		return armor;
	}
	
	public String getWeapon() {
		return weapon;
	}

	public abstract void takeTurn(Character target);
	
}
