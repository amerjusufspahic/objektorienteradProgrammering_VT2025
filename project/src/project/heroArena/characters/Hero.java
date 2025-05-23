package project.heroArena.characters;

import project.heroArena.data.GameLogger;

public class Hero extends Character{
	public Hero(String name) {
		super(name, 30);
	}
	
	@Override
	public void takeTurn(Character target) {
		int weaponDamage = getEquippedWeapon() != null ? getEquippedWeapon().getDamage() : 1;
	    int targetArmor = target.getEquippedArmor() != null ? target.getEquippedArmor().getDefense() : 0;

	    int damage = Math.max(0, weaponDamage - targetArmor);

	    GameLogger.logCombat(getName(), target.getName(), damage);
	    target.receiveDamage(damage);
	}
}
