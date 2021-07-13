package com.jscisco.gdx.game;

import com.artemis.World;
import com.artemis.io.SaveFileFormat;
import com.artemis.managers.WorldSerializationManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadSystem {

    public static void load(World world) {
        WorldSerializationManager manager = world.getSystem(WorldSerializationManager.class);
        Path path = Paths.get("save_game.json");
        try {
            InputStream is = new FileInputStream(path.toFile());
            manager.load(is, SaveFileFormat.class);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
