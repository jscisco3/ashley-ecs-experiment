package com.jscisco.gdx.screens;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.gdx.Position;
import com.jscisco.gdx.ecs.components.MovementComponent;
import com.jscisco.gdx.ecs.components.Player;
import com.jscisco.gdx.ecs.components.PositionComponent;
import com.jscisco.gdx.game.SaveSystem;
import com.jscisco.gdx.game.StarterGame;
import com.jscisco.gdx.input.MyInputProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * This is the main screen used during the dungeon adventure
 * <p>
 * It takes the Game and World objects in order to have the right context.
 */
public class GameScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);
    private final World world;

    // Input handlers
    private MyInputProcessor inputProcessor;
    private float initialInputDelay = 0.25f;

    public GameScreen(StarterGame game, World world, SpriteBatch batch) {
        super(game, batch);
        this.world = world;
        // TODO: Inject?
        this.inputProcessor = new MyInputProcessor();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.inputProcessor);
        logger.info("Set input processor...");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        handleInput(delta);
        // TODO: Do I need to clamp this?
        world.setDelta(delta);
        batch.begin();
        world.process();
        batch.end();
    }

    public void handleInput(float delta) {
        Set<Integer> keysDown = inputProcessor.getKeysDown();
        if (inputProcessor.isAnyKeyDown() && inputProcessor.getKeyPressedTime() == 0f) {
            setPlayerAction(keysDown);
        }
        if (inputProcessor.isAnyKeyDown()) {
            inputProcessor.setKeyPressedTime(inputProcessor.getKeyPressedTime() + delta);
        }
        if (inputProcessor.isAnyKeyDown() && inputProcessor.getKeyPressedTime() > initialInputDelay) {
            setPlayerAction(keysDown);
        }
        if (!inputProcessor.isAnyKeyDown()) {
            inputProcessor.setKeyPressedTime(0f);
        }
    }

    // TODO: Is there a better way of adding this information?
    private void setPlayerAction(Set<Integer> input) {
        ComponentMapper<PositionComponent> pcm = world.getMapper(PositionComponent.class);
        ComponentMapper<MovementComponent> mcm = world.getMapper(MovementComponent.class);
        int player = world.getAspectSubscriptionManager()
                .get(Aspect.all(Player.class))
                .getEntities().get(0);
        if (input.contains(Input.Keys.UP)) {
            // Add new MovementComponent with appropriate new position
            mcm.create(player)
                    .setPosition(
                            pcm.get(player).getPosition()
                                    .add(Position.of(0, 1))
                    );
        }
        if (input.contains(Input.Keys.DOWN)) {
            mcm.create(player)
                    .setPosition(
                            pcm.get(player).getPosition()
                                    .add(Position.of(0, -1))
                    );
        }
        if (input.contains(Input.Keys.LEFT)) {
            mcm.create(player)
                    .setPosition(
                            pcm.get(player).getPosition()
                                    .add(Position.of(-1, 0))
                    );
        }
        if (input.contains(Input.Keys.RIGHT)) {
            mcm.create(player)
                    .setPosition(
                            pcm.get(player).getPosition()
                                    .add(Position.of(1, 0))
                    );
        }
        if (input.contains(Input.Keys.ESCAPE)) {
            SaveSystem.save(world);
            Gdx.app.exit();
        }
    }

}
