package com.narxoz.rpg.combat;

public class IceShield implements Ability {
    private final String name = "Ice Shield";
    private final int damage =0;
    private final String description ="Defensive buff that increases protection.";

    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public int getDamage(){
        return this.damage;
    }
    @Override
    public String getDescription(){
        return this.description;
    }

    @Override
    public Ability clone() {
        return new IceShield();
    }
}
