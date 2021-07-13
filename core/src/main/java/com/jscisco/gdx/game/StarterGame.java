package com.jscisco.gdx.game;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.io.JsonArtemisSerializer;
import com.artemis.managers.WorldSerializationManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.gdx.Position;
import com.jscisco.gdx.ecs.components.Player;
import com.jscisco.gdx.ecs.components.PositionComponent;
import com.jscisco.gdx.ecs.components.RenderableComponent;
import com.jscisco.gdx.ecs.components.TextureReference;
import com.jscisco.gdx.ecs.systems.MovementSystem;
import com.jscisco.gdx.ecs.systems.RenderingSystem;
import com.jscisco.gdx.ecs.systems.TextureResolver;
import com.jscisco.gdx.repositories.TextureRepository;
import com.jscisco.gdx.screens.GameScreen;

import java.nio.file.Files;
import java.nio.file.Paths;

public class StarterGame extends ApplicationAdapter {
    SpriteBatch batch;
    //	Engine engine;
    World world;
    WorldSerializationManager manager;
    boolean saved = false;
    Screen screen;

    public void setScreen(Screen screen) {
        this.screen = screen;
        this.screen.show();
    }

    public Screen getScreen() {
        return screen;
    }

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
        if (!Files.exists(Paths.get("save_game.json"))) {
            int player = world.create();
            world.getMapper(PositionComponent.class).create(player).setPosition(Position.of(5, 5));
            world.getMapper(RenderableComponent.class).create(player).setTexture(TextureRepository.BAT);
            world.getMapper(TextureReference.class).create(player).setTextureReference("bat.png");
            world.getMapper(Player.class).create(player);

            // Add a basic entity
            for (int i = 0; i < 10; i++) {
                int e = world.create();
                world.getMapper(PositionComponent.class).create(e).setPosition(Position.of(i, i));
                world.getMapper(RenderableComponent.class).create(e).setTexture(TextureRepository.BAT);
                world.getMapper(TextureReference.class).create(e).setTextureReference("bat.png");
            }
        } else {
            LoadSystem.load(world);
        }

        setScreen(new GameScreen(this, world, batch));
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
