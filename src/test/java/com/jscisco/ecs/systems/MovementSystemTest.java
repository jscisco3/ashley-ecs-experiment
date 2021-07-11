package com.jscisco.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jscisco.ecs.Position;
import com.jscisco.ecs.components.MovementComponent;
import com.jscisco.ecs.components.PositionComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovementSystemTest {

    Engine engine;

    @BeforeEach
    public void setup() {
        engine = new Engine();
        engine.addSystem(new MovementSystem());
    }

    @Test
    public void entityWithMovementAndPositionComponents_isMovedWhenEngineTicks() {
        Entity e = engine.createEntity();

        e.add(PositionComponent.withPosition(Position.of(1, 1)));
        e.add(MovementComponent.to(Position.of(5, 5)));

        engine.addEntity(e);

        engine.update(0.0f);

        ComponentMapper<PositionComponent> pcm = ComponentMapper.getFor(PositionComponent.class);

        assertThat(pcm.get(e).getPosition()).isEqualTo(Position.of(5, 5));
    }

}
