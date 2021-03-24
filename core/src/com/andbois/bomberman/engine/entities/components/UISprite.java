package com.andbois.bomberman.engine.entities.components;

import com.andbois.bomberman.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;

public class UISprite extends Renderer {

    private Texture texture;

    private int screenX;
    private int screenY;

    private int screenWidth;
    private int screenHeight;

    public UISprite(Texture texture, int screenX, int screenY, int screenWidth, int screenHeight) {
        this.texture = texture;
        this.screenX = screenX;
        this.screenY = screenY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    @Override
    public void render(SpriteBatch batch) {
        Game game = entity.getLevel().getGame();
        Camera camera = entity.getLevel().getGame().getCamera();

        Vector3 pos = camera.unproject(new Vector3(screenX, screenY, 0));
        Vector2 size = new Vector2(
                screenHeight / (Gdx.graphics.getWidth() / game.getCameraSizeHorizontal()),
                screenWidth / (Gdx.graphics.getHeight() / game.getCameraSize()));

        batch.draw(texture, pos.x, pos.y, size.x, size.y);
    }
    @Override
    public void onInit() {

    }

    @Override
    public void onTick(float deltaTime) {

    }

    @Override
    public void onEnd() {

    }
}
