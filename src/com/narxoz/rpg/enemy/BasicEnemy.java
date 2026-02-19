package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class BasicEnemy implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private String aiBehavior;

    private List<Ability> abilities;
    private LootTable lootTable;

    public BasicEnemy(String name, int health, int damage, int defense, int speed,
                      String element, List<Ability> abilities, LootTable lootTable, String aiBehavior) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.element = element;
        this.aiBehavior = aiBehavior;
        this.abilities = (abilities == null) ? new ArrayList<>() : new ArrayList<>(abilities);
        this.lootTable = lootTable;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDefense() {
        return defense;
    }
    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public List<Ability> getAbilities() {
        return List.copyOf(abilities);
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (Basic Enemy) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element + " | AI: " + aiBehavior);
        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - " + a.getName() + " (" + a.getDamage() + "): " + a.getDescription());
        }
        if (lootTable != null) {
            System.out.println("Loot: " + lootTable.getItems()
                    + " | Gold: " + lootTable.getGoldDrop()
                    + " | EXP: " + lootTable.getExperienceDrop());
        } else {
            System.out.println("Loot: none");
        }
    }
    @Override
    public Enemy clone() {
        List<Ability> abilitiesCopy = new ArrayList<>();
        for (Ability a : abilities) {
            abilitiesCopy.add(a == null ? null : a.clone());
        }
        LootTable lootCopy = (lootTable == null) ? null : lootTable.clone();
        return new BasicEnemy(name, health, damage, defense, speed, element, abilitiesCopy, lootCopy, aiBehavior);
    }
    public void addAbility(Ability ability) {
        if (ability != null) abilities.add(ability);
    }

    public void multiplyStats(double m) {
        health = (int) Math.round(health * m);
        damage = (int) Math.round(damage * m);
        defense = (int) Math.round(defense * m);
        speed = (int) Math.round(speed * m);
    }
}
