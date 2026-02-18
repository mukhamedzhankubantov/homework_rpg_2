package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnemyRegistry {
    private final Map<String, Enemy> templates = new HashMap<>();
    public void registerTemplate(String key, Enemy template){
        if (key ==null || key.isBlank()){
            throw new IllegalArgumentException("Key must not be empty");
        }
        if (template ==null){
            throw new IllegalArgumentException("Template must not be null");
        }
        templates.put(key,template);
    }
    public Enemy createFromTemplate(String key){
        Enemy template = templates.get(key);
        if (template == null){
            throw new IllegalArgumentException("unknown template:"+key);
        }
        return template.clone();
    }
    public Set<String> listTemplates(){
        return Collections.unmodifiableSet(templates.keySet());
    }
    public Map<String,Enemy> getTemplatesView(){
        return Collections.unmodifiableMap(templates);
    }
}
