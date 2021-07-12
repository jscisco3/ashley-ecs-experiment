package com.jscisco.gdx.ecs.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.gdx.Position;
import com.jscisco.gdx.ecs.components.PositionComponent;
import com.jscisco.gdx.ecs.components.RenderableComponent;

@All({PositionComponent.class, RenderableComponent.class})
public class RenderingSystem extends IteratingSystem {
    // TODO: Sorting?

    private ComponentMapper<PositionComponent> pcm;
    private ComponentMapper<RenderableComponent> rcm;
    private SpriteBatch batch;

    public RenderingSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    protected void process(int entityId) {
        Position position = pcm.get(entityId).getPosition();
        Texture texture = rcm.get(entityId).getTexture();

        batch.draw(texture,
                position.getX() * texture.getWidth(),
                position.getY() * texture.getHeight()
        );
    }
}
