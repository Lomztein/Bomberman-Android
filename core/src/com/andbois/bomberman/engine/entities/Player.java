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
        rectangle = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, rectangle.getX() - (texture.getWidth() / 2), rectangle.getY() - (texture.getHeight() / 2));
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
}
