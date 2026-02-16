package com.narxoz.rpg.combat;

public class ShadowStrike implements Ability{
    private final String name = "Shadow Strike";
    private final int damage =60;
    private final String description ="High single-target damage and applies blind.";

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
        return new ShadowStrike();
    }
}
