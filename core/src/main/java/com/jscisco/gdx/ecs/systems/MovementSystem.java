package com.jscisco.gdx.ecs.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.jscisco.gdx.ecs.components.MovementComponent;
import com.jscisco.gdx.ecs.components.PositionComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@All({PositionComponent.class, MovementComponent.class})
public class MovementSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> pcm;
    private ComponentMapper<MovementComponent> mcm;

    private static final Logger logger = LoggerFactory.getLogger(MovementSystem.class);

    public MovementSystem() {
    }

    @Override
    protected void process(int entityId) {
        PositionComponent position = pcm.get(entityId);
        MovementComponent movement = mcm.get(entityId);
        position.setPosition(movement.getPosition());
    }
}
