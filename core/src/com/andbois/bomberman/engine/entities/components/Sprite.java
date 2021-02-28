package com.andbois.bomberman.engine.entities.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprite extends Renderer {

    private Texture texture;

    public Sprite (Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, transform.getX(), transform.getY());
    }

    @Override
    public void onTick(float deltaTime) {
    }

    @Override
    public void onEnd() {
        texture.dispose();
    }
}
