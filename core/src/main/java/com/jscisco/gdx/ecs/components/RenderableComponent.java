package com.jscisco.gdx.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class RenderableComponent implements Component {

    private final Texture texture;

    private RenderableComponent(Texture texture) {
        this.texture = texture;
    }

    public static RenderableComponent forTexture(Texture texture) {
        return new RenderableComponent(texture);
    }

    public Texture getTexture() {
        return texture;
    }
}
