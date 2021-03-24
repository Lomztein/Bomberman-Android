package com.andbois.bomberman.game.entities.components;

import com.andbois.bomberman.engine.entities.components.Component;

public class BombExplosion extends Component {

    private long spawnTime = 0;

    public BombExplosion() {
        spawnTime = System.currentTimeMillis();
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onTick(float deltaTime) {
        long passedTime = System.currentTimeMillis() - spawnTime;
        if(passedTime > 1500) {
            entity.getLevel().removeEntity(entity);
        }
    }

    @Override
    public void onEnd() {

    }
}