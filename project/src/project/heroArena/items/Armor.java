package project.heroArena.items;

public class Armor extends Item{
	private int defense;
	
	public Armor(String name, int defense) {
		super(name);
		this.defense = defense;
		
	}
	
	public int getDefense() {
		return defense;
	}
	
	@Override
	public String getType() {
		return "Armor";
	}
}
