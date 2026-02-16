package com.narxoz.rpg.combat;

public class FlameBreath implements Ability {

    private final String name = "Flame Breath";
    private final int damage =50;
    private final String description = "AoE fire damage and applies burn.";

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
    public Ability clone(){
        return new FlameBreath();
    }
}
