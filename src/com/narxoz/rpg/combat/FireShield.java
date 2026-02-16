package com.narxoz.rpg.combat;

public class FireShield implements Ability{

    private final String name ="Fire Shield";
    private final int damage =0;
    private final String description ="Defensive buff that reduces incoming damage.";

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
        return new FireShield();
    }

}
