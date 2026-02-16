package com.narxoz.rpg.combat;

public class Vanish implements Ability{
    private final String name = "Vanish";
    private final int damage =0;
    private final String description ="Stealth/evasion ability that helps avoid attacks.";

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
        return new Vanish();
    }
}
