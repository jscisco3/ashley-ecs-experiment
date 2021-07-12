package com.jscisco.gdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.gdx.Position;
import com.jscisco.gdx.ecs.components.PositionComponent;
import com.jscisco.gdx.ecs.components.RenderableComponent;
import com.jscisco.gdx.ecs.systems.MovementSystem;
import com.jscisco.gdx.ecs.systems.RenderingSystem;
import com.jscisco.gdx.repositories.TextureRepository;

public class StarterGame extends ApplicationAdapter {
	SpriteBatch batch;
	Engine engine;


	@Override
	public void create () {
		batch = new SpriteBatch();
		engine = new Engine();

		engine.addSystem(new MovementSystem());
		engine.addSystem(new RenderingSystem(batch));

		// Add a basic entity
		for (int i = 0; i < 10; i++) {
			Entity e = engine.createEntity();
			e.add(PositionComponent.withPosition(Position.of(i, i)));
			e.add(RenderableComponent.forTexture(TextureRepository.BAT));
			engine.addEntity(e);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Process Systems Here
		batch.begin();
		engine.update(0.0f);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
