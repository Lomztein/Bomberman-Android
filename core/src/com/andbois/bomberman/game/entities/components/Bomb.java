package com.andbois.bomberman.game.entities.components;

import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.engine.entities.components.Component;
import com.andbois.bomberman.engine.entities.components.Renderer;
import com.andbois.bomberman.engine.entities.components.Sprite;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bomb extends Component {

    private String textureExplosionName;
    private int duration = 0;

    private long spawnTime = 0;

    public Bomb(String textureExplosionName, int duration) {
        this.textureExplosionName = textureExplosionName;
        this.duration = duration;

        spawnTime = System.currentTimeMillis();
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onTick(float deltaTime) {
        long passedTime = System.currentTimeMillis() - spawnTime;
        if(passedTime > duration) {
            // Horizontal explosion
            BombExplosion horizontalExplosion = new BombExplosion();
            AABBCollider horizontalCollider = new AABBCollider(5, 1);
            entity.getLevel().addEntity(
                    entity.getLevel().makeEntity(
                            new Transform(entity.getComponent(Transform.class).getX(), entity.getComponent(Transform.class).getY(), 0),
                            horizontalExplosion, horizontalCollider, new Sprite(new Texture(textureExplosionName), 5, 1)));

            // Vertical explosion
            BombExplosion verticalExplosion = new BombExplosion();
            AABBCollider verticalCollider = new AABBCollider(1, 5);
            entity.getLevel().addEntity(
                    entity.getLevel().makeEntity(
                            new Transform(entity.getComponent(Transform.class).getX(), entity.getComponent(Transform.class).getY(), 0),
                            verticalExplosion, verticalCollider, new Sprite(new Texture(textureExplosionName), 1, 5)));

            entity.getLevel().removeEntity(entity);
        }
    }

    @Override
    public void onEnd() {

    }
}
