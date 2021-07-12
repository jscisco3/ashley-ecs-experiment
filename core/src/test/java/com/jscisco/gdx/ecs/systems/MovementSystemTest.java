package com.jscisco.gdx.ecs.systems;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.jscisco.gdx.Position;
import com.jscisco.gdx.ecs.components.MovementComponent;
import com.jscisco.gdx.ecs.components.PositionComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovementSystemTest {

    World world;
    ComponentMapper<PositionComponent> pcm;
    ComponentMapper<MovementComponent> mcm;

    @BeforeEach
    public void setup() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new MovementSystem()
                ).build();
        world = new World(config);
        pcm = world.getMapper(PositionComponent.class);
        mcm = world.getMapper(MovementComponent.class);
    }

    @Test
    public void entityWithMovementAndPositionComponents_isMovedWhenEngineTicks() {
        int e = world.create();

        PositionComponent p = pcm.create(e);
        p.setPosition(Position.of(1, 1));
        MovementComponent m = mcm.create(e);
        m.setPosition(Position.of(5, 5));

        world.process();

        assertThat(pcm.get(e).getPosition()).isEqualTo(Position.of(5, 5));
    }

}
