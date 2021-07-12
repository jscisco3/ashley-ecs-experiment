package com.jscisco.gdx.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.utils.IntBag;
import com.jscisco.gdx.ecs.components.DamageComponent;
import com.jscisco.gdx.ecs.components.HealthComponent;
import com.jscisco.gdx.ecs.components.MovementComponent;
import com.jscisco.gdx.ecs.components.PositionComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DamageSystemTest {

    World world;
    ComponentMapper<HealthComponent> hcm;
    ComponentMapper<DamageComponent> dcm;

    @BeforeEach
    public void setup() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new DamageSystem()
                ).build();
        world = new World(config);
        hcm = world.getMapper(HealthComponent.class);
        dcm = world.getMapper(DamageComponent.class);
    }

    @Test
    public void givenAnEntityWithHealthAndDamage_whenWorldIsProcessed_thenTheCurrentHealthIsReduced() {
        int entity = createEntityWithHealth(100);
        dcm.create(entity).setDamage(10);

        world.process();

        HealthComponent health = hcm.get(entity);
        assertThat(health.getCurrent()).isEqualTo(90);
    }

    @Test
    public void givenAnEntityThatTakesMoreDamageThanHealth_whenWorldIsProcessed_thenTheEntityIsRemoved() {
        int entity = createEntityWithHealth(100);
        dcm.create(entity).setDamage(10000);

        world.process();

        IntBag entities = world.getAspectSubscriptionManager()
                .get(Aspect.all())
                .getEntities();

        assertThat(entities.isEmpty()).isTrue();
    }

    private int createEntityWithHealth(int healthAmount) {
        int entity = world.create();
        HealthComponent health = hcm.create(entity);
        health.setMax(healthAmount);
        health.setCurrent(healthAmount);
        return entity;
    }
}
