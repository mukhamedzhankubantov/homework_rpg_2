package com.narxoz.rpg.builder;

import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public class EnemyDirector {

    private final EnemyBuilder builder;
    public EnemyDirector(EnemyBuilder builder){
        this.builder=builder;
    }

    public Enemy createMinion(EnemyComponentFactory factory) {
        return builder
                .setName("Minion")
                .setHealth(80)
                .setDamage(10)
                .setDefense(3)
                .setSpeed(30)
                .setElement("NONE")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .build();
    }

    public Enemy createElite(EnemyComponentFactory factory) {
        return builder
                .setName("Elite")
                .setHealth(250)
                .setDamage(35)
                .setDefense(12)
                .setSpeed(25)
                .setElement("NONE")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .build();
    }

    public Enemy createMiniBoss(EnemyComponentFactory factory) {
        return builder
                .setName("Mini Boss")
                .setHealth(2000)
                .setDamage(120)
                .setDefense(60)
                .setSpeed(20)
                .setElement("NONE")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .addPhase(1, 2000)
                .addPhase(2, 1000)
                .build();
    }

    public Enemy createRaidBoss(EnemyComponentFactory factory) {
        return builder
                .setName("Raid Boss")
                .setHealth(50000)
                .setDamage(500)
                .setDefense(200)
                .setSpeed(50)
                .setElement("NONE")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .addPhase(1, 50000)
                .addPhase(2, 30000)
                .addPhase(3, 15000)
                .build();
    }
}
