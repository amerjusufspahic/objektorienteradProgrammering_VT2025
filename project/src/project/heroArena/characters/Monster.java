package project.heroArena.characters;

import project.heroArena.data.GameLogger;
import project.heroArena.items.*;
import project.heroArena.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Character {

    public Monster(String name) {
        super(name, 30);
    }

    @Override
    public void takeTurn(Character target) {
        maybeSwitchWeapon();
        maybeSwitchArmor();

        int weaponDamage = getEquippedWeapon() != null ? getEquippedWeapon().getDamage() : 1;
        int targetDefense = target.getEquippedArmor() != null ? target.getEquippedArmor().getDefense() : 0;
        int damage = Math.max(0, weaponDamage - targetDefense);

        GameLogger.logCombat(getName(), target.getName(), damage);
        target.receiveDamage(damage);
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    private void maybeSwitchWeapon() {
        if (RandomUtil.chance(30)) {
            List<Weapon> weapons = new ArrayList<>();
            for (Item item : inventory) {
                if (item instanceof Weapon) {
                    weapons.add((Weapon) item);
                }
            }
            if (!weapons.isEmpty()) {
                Weapon newWeapon = RandomUtil.getRandomElement(weapons);
                equipWeapon(newWeapon.getName());
                GameLogger.logEquip(getName(), newWeapon.getName());
            }
        }
    }

    private void maybeSwitchArmor() {
        if (RandomUtil.chance(20)) {
            List<Armor> armors = new ArrayList<>();
            for (Item item : inventory) {
                if (item instanceof Armor) {
                    armors.add((Armor) item);
                }
            }
            if (!armors.isEmpty()) {
                Armor newArmor = RandomUtil.getRandomElement(armors);
                equipArmor(newArmor.getName());
                GameLogger.logEquip(getName(), newArmor.getName());
            }
        }
    }
}