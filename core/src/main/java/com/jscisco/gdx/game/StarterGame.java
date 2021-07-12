package com.jscisco.gdx.game;

import com.artemis.Aspect;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.io.JsonArtemisSerializer;
import com.artemis.io.SaveFileFormat;
import com.artemis.managers.WorldSerializationManager;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.gdx.Position;
import com.jscisco.gdx.ecs.components.PositionComponent;
import com.jscisco.gdx.ecs.components.RenderableComponent;
import com.jscisco.gdx.ecs.components.TextureReference;
import com.jscisco.gdx.ecs.systems.MovementSystem;
import com.jscisco.gdx.ecs.systems.RenderingSystem;
import com.jscisco.gdx.ecs.systems.TextureResolver;
import com.jscisco.gdx.repositories.TextureRepository;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StarterGame extends ApplicationAdapter {
    SpriteBatch batch;
    //	Engine engine;
    World world;
    WorldSerializationManager manager;
    boolean saved = false;


    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new WorldSerializationManager();

        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new TextureResolver(),
                        new MovementSystem(),
                        new RenderingSystem(batch)
                ).build()
                .setSystem(manager);
        world = new World(config);
        manager.setSerializer(new JsonArtemisSerializer(world));


//		// Add a basic entity
//		for (int i = 0; i < 10; i++) {
//			int e = world.create();
//			world.getMapper(PositionComponent.class).create(e).setPosition(Position.of(i, i));
//			world.getMapper(RenderableComponent.class).create(e).setTexture(TextureRepository.BAT);
//			world.getMapper(TextureReference.class).create(e).setTextureReference("bat.png");
//	}
        load();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Process Systems Here
        batch.begin();
        // TODO
        world.setDelta(0.0f);
        world.process();
        batch.end();

//		if (!saved) {
//			save();
//		}
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void load() {
        Path path = Paths.get("save_game.json");
        try {
            InputStream is = new FileInputStream(path.toFile());
            manager.load(is, SaveFileFormat.class);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        this.saved = true;
        // Save here
        Path path = Paths.get("save_game.json");
        try {
            FileOutputStream fos = new FileOutputStream(path.toFile(), false);
            // Collect the entities
            EntitySubscription entitySubscription = world.getAspectSubscriptionManager().get(Aspect.all());
            IntBag entities = entitySubscription.getEntities();

            manager.save(fos, new SaveFileFormat(entities));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
