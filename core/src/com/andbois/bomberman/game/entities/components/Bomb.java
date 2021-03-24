package com.andbois.bomberman.game.entities.components;

import com.andbois.bomberman.engine.entities.components.Component;
import com.andbois.bomberman.engine.entities.components.Renderer;
import com.andbois.bomberman.engine.entities.components.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bomb extends Component {

    private Texture texture;
    private Texture textureExplosion;
    private Sprite sprite;

    private int duration = 0;

    private long spawnTime = 0;
    private Boolean hasExploded = false;

    public Bomb(String textureName, String textureExplosionName, int posX, int posY, int duration) {
        texture = new Texture(textureName);
        textureExplosion = new Texture(textureExplosionName);

        this.duration = duration;
        spawnTime = System.currentTimeMillis();
    }

    @Override
    public void onInit() {
        sprite = entity.getComponent(Sprite.class);
        sprite.setTexture(texture);
    }

    @Override
    public void onTick(float deltaTime) {
        long passedTime = System.currentTimeMillis() - spawnTime;
        if(!hasExploded && passedTime > duration) {
            sprite.setTexture(textureExplosion);
            hasExploded = true;
        }
        else if(hasExploded && passedTime > duration + 1000) {
            entity.getLevel().removeEntity(entity);
        }
    }

    @Override
    public void onEnd() {

    }
}
