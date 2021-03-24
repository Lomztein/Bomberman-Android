package com.andbois.bomberman.engine.entities.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprite extends Renderer {

    private Texture texture;
    private float width;
    private float height;

    public Sprite (Texture texture, float width, float height) {
        this.texture = texture; this.width = width; this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, entity.getTransform().getX() - width / 2f, entity.getTransform().getY() - height / 2f, width, height);
    }

    @Override
    public void onInit() {
    }

    @Override
    public void onTick(float deltaTime) {
    }

    @Override
    public void onEnd() {
        texture.dispose();
    }
}
