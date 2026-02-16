package com.narxoz.rpg.combat;

public class FrostBreath implements Ability{
    private final String name= "Frost Breath";
    private final int damage =45;
    private final String description ="Damage and applies slow";

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
        return new FrostBreath();
    }

}
