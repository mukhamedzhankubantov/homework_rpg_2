package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;

public class Goblin implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;
    private List<Ability> abilities;
    private LootTable lootTable;


    public Goblin(String name) {
        this.name = name;
        // Goblin stats: weak but fast
        this.health = 100;
        this.damage = 15;
        this.defense = 5;
        this.speed = 35;
        this.abilities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void displayInfo() {
        System.out.println("=== " + name + " (Goblin) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        //System.out.println("Abilities: " + abilities.size() + " ability(ies)");
        // TODO: Display abilities details
        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            if (a == null) continue;
            System.out.println("  - " + a.getName() + " (" + a.getDamage() + "): " + a.getDescription());
        }

        // TODO: Display loot table
        if (lootTable != null) {
            System.out.println("Loot: " + lootTable.getItems()
                    + " | Gold: " + lootTable.getGoldDrop()
                    + " | EXP: " + lootTable.getExperienceDrop());
        } else {
            System.out.println("Loot: none");
        }

    }

    @Override
    public int getDamage(){
        return this.damage;
    }
    @Override
    public int getDefense(){
        return this.defense;
    }
    @Override
    public int getSpeed(){
        return this.speed;
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
    public Enemy clone(){
        Goblin copy = new Goblin(this.name);
        copy.health=this.health;
        copy.damage=this.damage;
        copy.defense=this.defense;
        copy.speed=this.speed;
        copy.abilities=new ArrayList<>();
        if (this.abilities!=null){
            for (Ability a: this.abilities){
                copy.abilities.add(a==null ? null : a.clone());
            }
        }
        if (this.lootTable != null){
            copy.lootTable = this.lootTable.clone();
        }else {
            copy.lootTable =null;
        }
        return copy;
    }


    public void addAbility(Ability ability){
        if (ability != null){
            this.abilities.add(ability);
        }
    }
    public void multiplyStats(double multiplier){
        this.health= (int) Math.round(this.health * multiplier);
        this.damage =(int) Math.round(this.damage*multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed =(int) Math.round(this.speed * multiplier);
    }

}
