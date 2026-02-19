package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class IceLootTable implements LootTable{
    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public IceLootTable(){
        this.items = new ArrayList<>();
        this.items.add("Ice Gems");
        this.items.add("Frost Scales");
        this.items.add("Ice Runes");
        this.goldDrop = 180;
        this.experienceDrop = 450;
    }
    public IceLootTable(List<String> items, int gold, int exp){
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
        return new IceLootTable(itemsCopy, this.goldDrop, this.experienceDrop);
    }

}
