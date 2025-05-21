package project.heroArena.characters;

import java.util.Random;
public class Monster extends Character{
	private static final Random random = new Random();
	
	public Monster(String name) {
		super(name, 30);
	}
	
	@Override
	public void takeTurn(Character target) {
		maybeSwitchWeapon();
		maybeSwitchArmor();
		
		int weaponDamage = 5;
		int armorDefense = 2;
		
		int damage = Math.max(0, weaponDamage - armorDefense);
		
		System.out.println(getName() + " attacks " + target.getName() + " for " + damage + " damage.");
		target.receiveDamage(damage);
	}
	
	private void maybeSwitchWeapon() {
		int chance = 30;
		if(random.nextInt(100) < chance) {
			System.out.println(getName() + " switches weapon randomly.");
			equipWeapon("Short Sword");
			
		}
		
		
	}
	
	private void maybeSwitchArmor() {
		int chance = 20;
		if(random.nextInt(100) < chance) {
			System.out.println(getName() + " switches armor randomly.");
			equipArmor("Leather Armor");
		}
	}

}
