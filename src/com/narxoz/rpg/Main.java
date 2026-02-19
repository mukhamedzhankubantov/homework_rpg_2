package com.narxoz.rpg;

import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.FrostBreath;
import com.narxoz.rpg.enemy.BasicEnemy;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Enemy System - Creational Patterns Capstone ===\n");

        System.out.println("============================================");
        System.out.println("PART 1: ABSTRACT FACTORY - Themed Components");
        System.out.println("============================================\n");

        // Your Abstract Factory demonstration here...
        EnemyComponentFactory fire = new FireComponentFactory();
        EnemyComponentFactory ice = new IceComponentFactory();
        EnemyComponentFactory shadow = new ShadowComponentFactory();

        demoFactory("FIRE", fire);
        demoFactory("ICE", ice);
        demoFactory("SHADOW", shadow);




        System.out.println("============================================");
        System.out.println("PART 2: BUILDER - Complex Enemy Construction");
        System.out.println("============================================\n");

        // Your Builder demonstration here...
        EnemyComponentFactory fireFactory = new FireComponentFactory();
        Enemy basic = new BasicEnemyBuilder()
                .setName("Forest Goblin")
                .setHealth(120)
                .setDamage(20)
                .setDefense(6)
                .setSpeed(40)
                .setElement("FIRE")
                .setAbilities(fireFactory.createAbilities())
                .setLootTable(fireFactory.createLootTable())
                .setAI(fireFactory.createAIBehavior())
                .build();

        basic.displayInfo();
        System.out.println();

        Enemy raidBoss = new EnemyDirector(new BossEnemyBuilder()).createRaidBoss(fireFactory);
        raidBoss.displayInfo();



        System.out.println("============================================");
        System.out.println("PART 3: PROTOTYPE - Enemy Cloning & Variants");
        System.out.println("============================================\n");

        // Your Prototype demonstration here...
        EnemyRegistry registry= new EnemyRegistry();

        Goblin baseGoblin = new Goblin("Goblin Template");
        registry.registerTemplate("goblin", baseGoblin);

        // it is clone:
        Goblin cloneGoblin = (Goblin) registry.createFromTemplate("goblin");
        cloneGoblin.addAbility(new FrostBreath());
        System.out.println("Original template abilities:" +baseGoblin.getAbilities().size());
        System.out.println("Clone abilities:"+cloneGoblin.getAbilities().size());

        registry.registerTemplate("basic", basic);

        Enemy basicClone = registry.createFromTemplate("basic");
        BasicEnemy basicCloneCasted = (BasicEnemy) basicClone;
        basicCloneCasted.addAbility(new FrostBreath());

        System.out.println("\n--- BasicEnemy Deep Copy Proof ---");
        System.out.println("Template basic abilities: " + basic.getAbilities().size());
        System.out.println("Clone basic abilities: " + basicClone.getAbilities().size());


        // Dragon register
        registry.registerTemplate("dragon", raidBoss);
        Enemy eliteDragon =registry.createFromTemplate("dragon");
        DragonBoss elite = (DragonBoss) eliteDragon;
        elite.multiplyStats(2.0);


        Enemy ancientDragon =registry.createFromTemplate("dragon");
        DragonBoss ancient = (DragonBoss) ancientDragon;

        ancient.multiplyStats(5.0);
        ancient.addAbility(new FrostBreath());

        System.out.println("\n--- Dragon Variants ---");
        eliteDragon.displayInfo();
        ancientDragon.displayInfo();
        System.out.println("Template dragon abilities:" + raidBoss.getAbilities().size());
        System.out.println("Ancient dragon abilities:" + ancientDragon.getAbilities().size());



        System.out.println("============================================");
        System.out.println("PART 4: ALL PATTERNS WORKING TOGETHER");
        System.out.println("============================================\n");

        // Your integration demonstration here...
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();
        Enemy shadowBoss = new EnemyDirector(new BossEnemyBuilder()).createRaidBoss(shadowFactory);

        System.out.println("Template Shadow Boss:");
        shadowBoss.displayInfo();

        registry.registerTemplate("shadow-boss", shadowBoss);

        Enemy shadowEliteEnemy  =registry.createFromTemplate("shadow-boss");
        DragonBoss shadowElite = (DragonBoss) shadowEliteEnemy;
        shadowElite.multiplyStats(2.0);

        Enemy shadowMythicEnemy = registry.createFromTemplate("shadow-boss");
        DragonBoss shadowMythic = (DragonBoss) shadowMythicEnemy;
        shadowMythic.multiplyStats(5.0);
        shadowMythic.addAbility(new FrostBreath());

        System.out.println("\nCloned Shadow Variants: ");
        shadowElite.displayInfo();
        shadowMythic.displayInfo();

        System.out.println("Template abilities: "+ shadowBoss.getAbilities().size());
        System.out.println("Mythic abilities: "+ shadowMythic.getAbilities().size());

        System.out.println("============================================");
        System.out.println("PATTERN SUMMARY");
        System.out.println("============================================");
        System.out.println();
        System.out.println("Abstract Factory: Fire/Ice/Shadow factories create consistent themed abilities, loot, and AI.");
        System.out.println("Builder: BossEnemyBuilder + Director construct complex enemies step-by-step.");
        System.out.println("Factory Method: build() method creates Enemy objects (inside builders/director flow).");
        System.out.println("Prototype: EnemyRegistry clones templates with deep copy to generate variants.");

        System.out.println();
        System.out.println("Extensibility:");
        System.out.println("- New theme (Nature): add Nature abilities + NatureLootTable + NatureComponentFactory (no existing code changes).");
        System.out.println("- New enemy type (Lich): create Lich enemy class + (optional) LichBossBuilder or reuse BossEnemyBuilder.");
        System.out.println("- New difficulty tier (Mythic): clone from registry and multiplyStats(10.0) + add extra ability.");


        System.out.println("\n=== Demo Complete ===");
    }


    private static void demoFactory(String name, EnemyComponentFactory factory){
        System.out.println("---- "+ name +" THEME ---");
        System.out.println("AI: "+ factory.createAIBehavior());

        System.out.println("Abilities: ");
        for (var a: factory.createAbilities()){
            System.out.println(" - "+ a.getName() + " ("+ a.getDamage() +" ): "+a.getDescription());
        }
        var loot = factory.createLootTable();
        System.out.println("Loot items: "+loot.getItems());
        System.out.println("Gold: "+ loot.getGoldDrop()+ ", EXP: "+ loot.getExperienceDrop());
        System.out.println();
    }
}
