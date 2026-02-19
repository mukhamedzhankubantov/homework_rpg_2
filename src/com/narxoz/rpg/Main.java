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

/**
 * Main demonstration class for the RPG Enemy System.
 *
 * ============================================================
 * CREATIONAL PATTERNS CAPSTONE
 * ============================================================
 *
 * This demo must showcase ALL FOUR creational design patterns
 * working together in one unified system:
 *
 *   1. ABSTRACT FACTORY — Create themed enemy component families
 *   2. BUILDER          — Construct complex enemies step-by-step
 *   3. FACTORY METHOD   — Embedded in Builder.build() and Director
 *   4. PROTOTYPE        — Clone enemies into variants efficiently
 *
 * The patterns work together in a pipeline:
 *
 *   Abstract Factory (themed components)
 *          |
 *          v
 *   Builder (assembles enemy from components)
 *          |
 *          v  <-- Factory Method: build() produces the Enemy
 *   Prototype (clones built enemy into variants)
 *
 * ============================================================
 * YOUR TASKS:
 * ============================================================
 *
 * Your Main.java should demonstrate each pattern clearly,
 * then show them working together. Follow the structure below.
 *
 * Expected output flow:
 *   Part 1: Abstract Factory creates themed components
 *   Part 2: Builder constructs complex enemies
 *   Part 3: Prototype clones enemies into variants
 *   Part 4: Full pipeline — all patterns integrated
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Enemy System - Creational Patterns Capstone ===\n");

        // ============================================================
        // PART 1: ABSTRACT FACTORY PATTERN
        // ============================================================
        // TODO: Create themed component factories
        //   - FireComponentFactory
        //   - IceComponentFactory
        //   - ShadowComponentFactory
        //
        // TODO: Show that each factory creates MATCHING components
        //   EnemyComponentFactory fireFactory = new FireComponentFactory();
        //   List<Ability> fireAbilities = fireFactory.createAbilities();
        //   LootTable fireLoot = fireFactory.createLootTable();
        //   String fireAI = fireFactory.createAIBehavior();
        //
        // TODO: Display the components from each factory
        //   Show that Fire factory creates fire abilities + fire loot
        //   Show that Ice factory creates ice abilities + ice loot
        //   Show that they CANNOT be mixed (consistency guaranteed!)
        //
        // Think: How is this similar to HW1's EquipmentFactory?

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



        // ============================================================
        // PART 2: BUILDER PATTERN
        // ============================================================
        // TODO: Build complex enemies using your EnemyBuilder
        //
        // Build at least:
        //   - One complex boss (Dragon) using BossEnemyBuilder
        //     Use the FireComponentFactory to get themed components!
        //   - One medium enemy using BasicEnemyBuilder
        //
        // TODO: Show the fluent interface in action:
        //   Enemy dragon = new BossEnemyBuilder()
        //       .setName("Ancient Fire Dragon")
        //       .setHealth(50000)
        //       .setDamage(500)
        //       .setAbilities(fireFactory.createAbilities())
        //       .setLootTable(fireFactory.createLootTable())
        //       .addPhase(1, 50000)
        //       .addPhase(2, 30000)
        //       .addPhase(3, 15000)
        //       .build();
        //
        // TODO: Show the Director creating preset enemies:
        //   EnemyDirector director = new EnemyDirector(new BossEnemyBuilder());
        //   Enemy miniBoss = director.createMiniBoss();
        //   Enemy raidBoss = director.createRaidBoss();
        //
        // Think: Where is Factory Method here? (Hint: build() IS the factory method!)
        // Think: How does the Director use Factory Method delegation?

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


        // ============================================================
        // PART 3: PROTOTYPE PATTERN
        // ============================================================
        // TODO: Create a template registry and populate it
        //   EnemyRegistry registry = new EnemyRegistry();
        //   registry.registerTemplate("goblin", baseGoblin);
        //   registry.registerTemplate("dragon", baseDragon);
        //
        // TODO: Clone enemies to create difficulty variants
        //   Enemy eliteGoblin = registry.createFromTemplate("goblin");
        //   eliteGoblin.multiplyStats(2.0);  // 2x stats
        //
        // TODO: Clone enemies to create elemental variants
        //   Enemy fireDragon = registry.createFromTemplate("dragon");
        //   fireDragon.setElement("FIRE");
        //   fireDragon.setAbilities(fireFactory.createAbilities());
        //
        // TODO: Prove deep copy works!
        //   Modify cloned enemy's abilities.
        //   Show that the original template is UNCHANGED.
        //
        // Think: What would happen with shallow copy here?

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


        // ============================================================
        // PART 4: ALL PATTERNS WORKING TOGETHER
        // ============================================================
        // TODO: Show the full pipeline
        //
        // Step 1: Abstract Factory creates Shadow components
        //   EnemyComponentFactory shadowFactory = new ShadowComponentFactory();
        //
        // Step 2: Builder assembles Demon Lord with Shadow components
        //   Enemy demonLord = new BossEnemyBuilder()
        //       .setName("Demon Lord")
        //       .setAbilities(shadowFactory.createAbilities())
        //       .setLootTable(shadowFactory.createLootTable())
        //       .build();
        //
        // Step 3: Register as Prototype template
        //   registry.registerTemplate("demon-lord", demonLord);
        //
        // Step 4: Clone variants
        //   Enemy greaterDemon = registry.createFromTemplate("demon-lord");
        //   greaterDemon.multiplyStats(2.0);
        //
        // Display all variants showing each pattern's contribution!

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

        // ============================================================
        // SUMMARY
        // ============================================================
        System.out.println("============================================");
        System.out.println("PATTERN SUMMARY");
        System.out.println("============================================");
        System.out.println();
        System.out.println("Abstract Factory: Fire/Ice/Shadow factories create consistent themed abilities, loot, and AI.");
        System.out.println("Builder: BossEnemyBuilder + Director construct complex enemies step-by-step.");
        System.out.println("Factory Method: build() method creates Enemy objects (inside builders/director flow).");
        System.out.println("Prototype: EnemyRegistry clones templates with deep copy to generate variants.");

        // TODO: Print a summary showing which patterns were demonstrated
        // Example:
        // System.out.println("Abstract Factory: Themed component families (Fire, Ice, Shadow)");
        // System.out.println("Builder: Complex step-by-step enemy construction");
        // System.out.println("Factory Method: Embedded in Builder.build() and Director");
        // System.out.println("Prototype: Efficient template cloning with deep copy");

        System.out.println("\n=== Demo Complete ===");
    }

    // TODO: Add helper methods as needed
    // Consider:
    // - displayEnemyDetails(Enemy enemy)
    // - demonstrateDeepCopy(Enemy original, Enemy clone)
    // - createThemeDemo(EnemyComponentFactory factory, String themeName)

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
