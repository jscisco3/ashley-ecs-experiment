package com.jscisco.gdx.ecs.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.jscisco.gdx.ecs.components.DamageComponent;
import com.jscisco.gdx.ecs.components.HealthComponent;

@All({HealthComponent.class, DamageComponent.class})
public class DamageSystem extends IteratingSystem {

    private ComponentMapper<HealthComponent> hcm;
    private ComponentMapper<DamageComponent> dcm;

    public DamageSystem() {
    }

    @Override
    protected void process(int entityId) {
        HealthComponent health = hcm.get(entityId);
        DamageComponent damage = dcm.get(entityId);
        health.subtract(damage.getDamage());
        dcm.remove(entityId);
        if (health.getCurrent() <= 0) {
            world.delete(entityId);
        }
    }
}
