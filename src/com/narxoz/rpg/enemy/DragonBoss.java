package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DragonBoss implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;


    private String element;
    private List<Ability> abilities;
    private Map<Integer, Integer> phases;
    private LootTable lootTable;
    private String aiBehavior;

    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    public DragonBoss(String name, int health, int damage, int defense,
                      int speed, String element,
                      List<Ability> abilities,
                      int phase1Threshold, int phase2Threshold, int phase3Threshold,
                      LootTable lootTable, String aiBehavior,
                      boolean canFly, boolean hasBreathAttack, int wingspan) {

        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.element = element;
        this.abilities = abilities != null ? abilities : new ArrayList<>();
        this.phases = new HashMap<>();
        this.phases.put(1, phase1Threshold);
        this.phases.put(2, phase2Threshold);
        this.phases.put(3, phase3Threshold);
        this.lootTable = lootTable;
        this.aiBehavior = aiBehavior;
        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }


    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void displayInfo() {
        System.out.println("=== " + name + " (Dragon Boss) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element);
        //System.out.println("Abilities (" + abilities.size() + "):");
        // TODO: Display each ability's details
        System.out.println("Abilities (" + (abilities == null ? 0 : abilities.size()) + "):");
        if (abilities != null){
            for (Ability a : abilities){
                if (a == null) continue;
                System.out.println("  - " + a.getName() + " (" + a.getDamage() + "): " + a.getDescription());
            }
        }

        System.out.println("Boss Phases: " + phases.size());
        for (Map.Entry<Integer, Integer> phase : phases.entrySet()) {
            System.out.println("  Phase " + phase.getKey()
                    + ": triggers at " + phase.getValue() + " HP");
        }
        System.out.println("AI Behavior: " + aiBehavior);
        System.out.println("Can Fly: " + canFly
                + " | Breath Attack: " + hasBreathAttack
                + " | Wingspan: " + wingspan);
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
    public Enemy clone() {
        List<Ability> abilitiesCopy = new ArrayList<>();
        if (this.abilities != null){
            for (Ability a: this.abilities){
                abilitiesCopy.add(a == null ? null: a.clone());
            }
        }
        Map<Integer,Integer> phasesCopy = new HashMap<>();
        if (this.phases !=null){
            phasesCopy.putAll(this.phases);
        }
        LootTable lootCopy = (this.lootTable==null) ? null: this.lootTable.clone();
        int p1 =phasesCopy.getOrDefault(1,this.health);
        int p2 =phasesCopy.getOrDefault(2,Math.max(1,this.health/2));
        int p3 =phasesCopy.getOrDefault(3,Math.max(1,this.health/4));
        return new DragonBoss(
                this.name,
                this.health,
                this.damage,
                this.defense,
                this.speed,
                this.element,
                abilitiesCopy,
                p1, p2, p3,
                lootCopy,
                this.aiBehavior,
                this.canFly,
                this.hasBreathAttack,
                this.wingspan
        );
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
        return new ArrayList<>(abilities);
    }
    @Override
    public LootTable getLootTable() {
        return lootTable;
    }


    public void multiplyStats(double m) {
        this.health = (int) Math.round(this.health *m);
        this.damage = (int) Math.round(this.damage *m);
        this.defense = (int) Math.round(this.defense*m);
        this.speed = (int) Math.round(this.speed *m);
        if (this.phases != null) {
            for (Map.Entry<Integer,Integer> e : this.phases.entrySet()) {
                e.setValue((int) Math.round(e.getValue() *m));
            }
        }
    }
    public void addAbility(Ability ability) {
        if (ability ==null) return;
        if (this.abilities == null) this.abilities= new ArrayList<>();
        this.abilities.add(ability);
    }

}
