package com.jscisco.gdx.game;

import com.artemis.Aspect;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.artemis.io.SaveFileFormat;
import com.artemis.managers.WorldSerializationManager;
import com.artemis.utils.IntBag;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveSystem {

    public static void save(World world) {
        WorldSerializationManager manager = world.getSystem(WorldSerializationManager.class);
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
