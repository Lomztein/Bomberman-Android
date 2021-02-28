package com.andbois.bomberman.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

public class Input implements InputProcessor {

    private static Input instance;
    private static int maxTouches = 10;
    private Touch[] touches;

    private Input () {
        touches = new Touch[maxTouches];
    }

    public static Input getInstance () {
        if (instance == null) {
            instance = new Input ();
            Gdx.input.setInputProcessor(instance);
        }
        return instance;
    }

    public static Touch[] getTouches () {
        Touch[] allTouches = getInstance().touches;

        int notNull = 0;
        for (int i = 0; i < allTouches.length; i++) {
            if (allTouches[i] != null) {
                notNull++;
            }
        }

        Touch[] toReturn = new Touch[notNull];
        int index = 0;

        for (int i = 0; i < allTouches.length; i++) {
            if (allTouches[i] != null) {
                toReturn[index] = allTouches[i];
                index++;
            }
        }

        return toReturn;
    }

    public static Touch getSingleTouch () {
        Touch[] touches = getTouches();
        if (touches.length == 0) {
            return null;
        }
        return touches[0];
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touches[pointer] = new Touch(pointer, screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touches[pointer] = null;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Touch touch = touches[pointer];

        int deltaX = screenX - touch.getX();
        int deltaY = screenY - touch.getY();

        touch.setX(screenX);
        touch.setY(screenY);
        touch.setDeltaX(deltaX);
        touch.setDeltaY(deltaY);

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
}
