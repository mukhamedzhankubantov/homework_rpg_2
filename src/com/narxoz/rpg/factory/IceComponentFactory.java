package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.FrostBreath;
import com.narxoz.rpg.combat.IceShield;
import com.narxoz.rpg.loot.IceLootTable;
import com.narxoz.rpg.loot.LootTable;


import java.util.ArrayList;
import java.util.List;

public class IceComponentFactory implements EnemyComponentFactory{
    @Override
    public List<Ability> createAbilities(){
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new IceShield());
        abilities.add(new FrostBreath());
        return abilities;
    }

    @Override
    public LootTable createLootTable(){
        return new IceLootTable();

    }
    @Override
    public String createAIBehavior(){
        return "DEFENSIVE";
    }
}
