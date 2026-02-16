package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class ShadowLootTable implements LootTable{
    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public ShadowLootTable(){
        this.items = new ArrayList<>();
        this.items.add("Shadow  Gems");
        this.items.add("Dark Essences");
        this.items.add("Shadow  Runes");
        this.goldDrop = 220;
        this.experienceDrop = 550;
    }
    public ShadowLootTable(List<String> items, int gold, int exp){
        this.items=items;
        this.goldDrop=gold;
        this.experienceDrop=exp;
    }

    @Override
    public List<String> getItems(){
        return this.items;
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
        return new ShadowLootTable(itemsCopy, this.goldDrop, this.experienceDrop);
    }

}
