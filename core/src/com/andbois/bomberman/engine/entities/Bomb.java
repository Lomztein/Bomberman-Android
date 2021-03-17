package com.andbois.bomberman.engine.entities;

import com.andbois.bomberman.engine.entities.components.Renderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bomb extends Renderer {
    private Texture texture;
    private Texture textureExplosion;
    private Rectangle rectangle;

    private int duration = 0;

    private long spawnTime = 0;
    private Boolean hasExploded = false;
    private Boolean shouldDispose = false;

    public Bomb(String textureName, String textureExplosionName, int posX, int posY, int duration) {
        texture = new Texture(textureName);
        textureExplosion = new Texture(textureExplosionName);
        rectangle = new Rectangle(posX - (texture.getWidth() / 2), posY - (texture.getHeight() / 2), texture.getWidth(), texture.getHeight());

        this.duration = duration;
        spawnTime = System.currentTimeMillis();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, rectangle.getX(), rectangle.getY());
    }

    @Override
    public void onTick(float deltaTime) {
        long passedTime = System.currentTimeMillis() - spawnTime;
        if(!hasExploded && passedTime > duration) {
            this.texture = textureExplosion;
            hasExploded = true;
        }
        else if(hasExploded && passedTime > duration + 1000) {
            shouldDispose = true;
        }
    }

    @Override
    public void onEnd() {

    }

    public Boolean getHasExploded() {
        return hasExploded;
    }

    public Boolean getShouldDispose() {
        return shouldDispose;
    }
}
