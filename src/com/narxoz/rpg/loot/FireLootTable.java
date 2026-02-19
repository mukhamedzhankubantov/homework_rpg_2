package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class FireLootTable implements LootTable{
    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public FireLootTable(){
        this.items = new ArrayList<>();
        this.items.add("Fire Gems");
        this.items.add("Dragon Scales");
        this.items.add("Flame Runes");
        this.goldDrop = 200;
        this.experienceDrop = 500;
    }
    public FireLootTable(List<String> items, int gold, int exp){
        this.items=items;
        this.goldDrop=gold;
        this.experienceDrop=exp;
    }

    @Override
    public List<String> getItems(){
        return List.copyOf(items);
    }
    @Override
    public int getGoldDrop(){
        return this.goldDrop;
    }
    @Override
    public int getExperienceDrop(){
        return this.experienceDrop;
    }

    @Override
    public LootTable clone() {
        List<String> itemsCopy = new ArrayList<>(this.items);
        return new FireLootTable(itemsCopy, this.goldDrop, this.experienceDrop);
    }

}
