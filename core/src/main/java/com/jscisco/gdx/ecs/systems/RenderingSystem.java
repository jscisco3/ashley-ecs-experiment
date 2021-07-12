package com.jscisco.gdx.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.gdx.ecs.components.PositionComponent;
import com.jscisco.gdx.ecs.components.RenderableComponent;

public class RenderingSystem extends IteratingSystem {
    // TODO: Sorting?

    private ComponentMapper<PositionComponent> pcm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<RenderableComponent> rcm = ComponentMapper.getFor(RenderableComponent.class);
    private SpriteBatch batch;

    public RenderingSystem(SpriteBatch batch) {
        super(Family.all(PositionComponent.class, RenderableComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        PositionComponent position = pcm.get(entity);
        RenderableComponent renderable = rcm.get(entity);

        this.batch.draw(
                renderable.getTexture(),
                position.getPosition().getX() * renderable.getTexture().getWidth(),
                position.getPosition().getY() * renderable.getTexture().getHeight()
        );
    }
}
