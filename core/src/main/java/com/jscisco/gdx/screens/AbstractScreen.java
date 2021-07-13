package com.jscisco.gdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jscisco.gdx.game.StarterGame;

/**
 * The shared logic of all screens. Contains some context necessary
 * to pass to other screens when transitioning.
 */
public class AbstractScreen extends ScreenAdapter {
    protected StarterGame game;
    // The UI elements go here
    protected Stage stage;
    protected SpriteBatch batch;

    public AbstractScreen(StarterGame game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        // TODO: Inject this?
        this.stage = new Stage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
    }

}
