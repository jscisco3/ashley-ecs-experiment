package com.jscisco.gdx.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.jscisco.gdx.ecs.components.MovementComponent;
import com.jscisco.gdx.ecs.components.PositionComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> pcm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<MovementComponent> mcm = ComponentMapper.getFor(MovementComponent.class);

    private static final Logger logger = LoggerFactory.getLogger(MovementSystem.class);

    public MovementSystem() {
        super(Family.all(PositionComponent.class, MovementComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        logger.info("Processing " + entity);
        PositionComponent position = pcm.get(entity);
        MovementComponent movement = mcm.get(entity);

        position.setPosition(movement.getPosition());
    }
}
