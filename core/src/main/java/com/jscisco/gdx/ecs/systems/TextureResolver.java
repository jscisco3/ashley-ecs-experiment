package com.jscisco.gdx.ecs.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;
import com.jscisco.gdx.ecs.components.RenderableComponent;
import com.jscisco.gdx.ecs.components.TextureReference;

import static com.artemis.Aspect.all;

public class TextureResolver extends BaseEntitySystem {

    ComponentMapper<RenderableComponent> rcm;
    ComponentMapper<TextureReference> trcm;

    public TextureResolver() {
        super(all(TextureReference.class).exclude(RenderableComponent.class));
    }

    @Override
    protected void processSystem() {

    }

    @Override
    public void inserted(int entity) {
        // TODO: Fix
        rcm.create(entity).setTexture(new Texture(trcm.get(entity).getTextureReference()));
    }
}
