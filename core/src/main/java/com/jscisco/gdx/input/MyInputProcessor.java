package com.jscisco.gdx.input;

import com.badlogic.gdx.InputProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class MyInputProcessor implements InputProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MyInputProcessor.class);

    private final Set<Integer> keysDown = new HashSet<>();
    private float keyPressedTime = 0f;

    public boolean isAnyKeyDown() {
        return !keysDown.isEmpty();
    }

    @Override
    public boolean keyDown(int keycode) {
        keysDown.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysDown.remove(keycode);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public Set<Integer> getKeysDown() {
        return keysDown;
    }

    public float getKeyPressedTime() {
        return keyPressedTime;
    }

    public void setKeyPressedTime(float keyPressedTime) {
        this.keyPressedTime = keyPressedTime;
    }
}
