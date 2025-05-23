package project.heroArena.items;

public class Weapon extends Item {
	private int damage;
	
	public Weapon(String name, int damage) {
		super(name);
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String getType() {
		return "Weapon";
	}
}
