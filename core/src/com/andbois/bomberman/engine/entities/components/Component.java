package com.andbois.bomberman.engine.entities.components;

import com.andbois.bomberman.engine.entities.Entity;

public abstract class Component {

    protected Entity entity;

    public void setEntity (Entity entity) {
        this.entity = entity;
    }

    public abstract void onInit ();

    public abstract void onTick (float deltaTime);

    public abstract void onEnd ();

}
