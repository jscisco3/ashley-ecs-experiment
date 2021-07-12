package com.jscisco.gdx.ecs.components;

import com.artemis.Component;

public class TextureReference extends Component {
    String textureReference;

    public TextureReference() {
    }

    public TextureReference(String textureReference) {
        this.textureReference = textureReference;
    }

    public String getTextureReference() {
        return textureReference;
    }

    public void setTextureReference(String textureReference) {
        this.textureReference = textureReference;
    }
}
