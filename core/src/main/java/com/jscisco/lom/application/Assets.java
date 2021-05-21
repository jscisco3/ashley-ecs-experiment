package com.jscisco.lom.application;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    private final AssetManager assetManager;

    /**
     * Assets go here
     */

    public static final AssetDescriptor<TextureAtlas> atlas = new AssetDescriptor<TextureAtlas>("packed/assets.atlas", TextureAtlas.class);

    // Terrain features
    public static final String floor = "floor";
    public static final String wall = "wall";
    public static final String stairsDown = "stairsDown";
    public static final String stairsUp = "stairsUp";
    // Entities
    public static final String warrior = "warrior";
    public static final String golem = "golem";
    // Items
    public static final String sword = "sword";
    public static final String ring = "ring";
    // Kingdom screen
    public static final String background = "parchmentAncient";
    public static final String inn = "inn";
    public static final String portal = "skull";

    public Assets() {
        this.assetManager = new AssetManager();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void load() {
        assetManager.load(atlas);
    }

    public TextureRegion getTextureRegion(String region) {
        return assetManager.get(atlas).findRegion(region);
    }

    public void dispose() {
        this.assetManager.dispose();
    }
}
