package com.jscisco.gdx.game;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.gdx.Position;
import com.jscisco.gdx.ecs.components.PositionComponent;
import com.jscisco.gdx.ecs.components.RenderableComponent;
import com.jscisco.gdx.ecs.systems.MovementSystem;
import com.jscisco.gdx.ecs.systems.RenderingSystem;
import com.jscisco.gdx.repositories.TextureRepository;

public class StarterGame extends ApplicationAdapter {
	SpriteBatch batch;
//	Engine engine;
	World world;


	@Override
	public void create () {
		batch = new SpriteBatch();
		WorldConfiguration config = new WorldConfigurationBuilder()
				.with(
						new MovementSystem(),
						new RenderingSystem(batch)
				).build();
		world = new World(config);
//		// Add a basic entity
		for (int i = 0; i < 10; i++) {
			int e = world.create();
			world.getMapper(PositionComponent.class).create(e).setPosition(Position.of(i, i));
			world.getMapper(RenderableComponent.class).create(e).setTexture(TextureRepository.BAT);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Process Systems Here
		batch.begin();
		// TODO
		world.setDelta(0.0f);
		world.process();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
