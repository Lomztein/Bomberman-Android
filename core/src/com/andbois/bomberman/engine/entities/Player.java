package com.andbois.bomberman.engine.entities;

import com.andbois.bomberman.engine.entities.components.Renderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Renderer {
    private Texture texture;
    private Rectangle rectangle;

    public Player(String textureName, int posX, int posY) {
        texture = new Texture(textureName);
        rectangle = new Rectangle(posX - (texture.getWidth() / 2), posY - (texture.getHeight() / 2), texture.getWidth(), texture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, rectangle.getX(), rectangle.getY());
    }

    @Override
    public void onTick(float deltaTime) {

    }

    @Override
    public void onEnd() {

    }

    public float getX() {
        return rectangle.getX();
    }

    public float getY() {
        return rectangle.getY();
    }

    public void setX(float x) {
        rectangle.setX(x);
    }

    public void setY(float y) {
        rectangle.setY(y);
    }

    public int getWidth() {
        return texture.getWidth();
    }

    public int getHeight() {
        return texture.getHeight();
    }
}
