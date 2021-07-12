package com.jscisco.gdx.ecs.components;

import com.artemis.Component;
import com.artemis.annotations.Transient;
import com.badlogic.gdx.graphics.Texture;

@Transient
public class RenderableComponent extends Component {

    private Texture texture;

    public RenderableComponent() {}

    private RenderableComponent(Texture texture) {
        this.texture = texture;
    }

    public static RenderableComponent forTexture(Texture texture) {
        return new RenderableComponent(texture);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
