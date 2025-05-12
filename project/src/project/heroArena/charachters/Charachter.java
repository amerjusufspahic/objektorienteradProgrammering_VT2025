package project.heroArena.charachters;

public abstract class Charachter {
	private String name;
	private int health;
	private String weapon;
	private String armor;
	
	
	public Charachter (String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	public void equipWeapon(String weapon) {
		this.weapon	= weapon;
	}
	
	public void equipArmor(String armor) {
		this.armor = armor;
	}
	
	public void recieveDamage(int damage) {
		
	}
	
	public abstract void takeTurn();
	
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
}
