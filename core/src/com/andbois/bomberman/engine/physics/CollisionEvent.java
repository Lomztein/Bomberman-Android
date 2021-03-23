package com.andbois.bomberman.engine.physics;

import com.andbois.bomberman.engine.Event;
import com.andbois.bomberman.engine.entities.components.AABBCollider;

public class CollisionEvent extends Event {

    private AABBCollider self;
    private AABBCollider other;

    private float deltaX;
    private float deltaY;

    private float normalX;
    private float normalY;

    private float posX;
    private float posY;

    public CollisionEvent(Object owner, AABBCollider self, AABBCollider other, float deltaX, float deltaY, float normalX, float normalY, float posX, float posY) {
        super(owner);
        this.self = self;
        this.other = other;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.normalX = normalX;
        this.normalY = normalY;
        this.posX = posX;
        this.posY = posY;
    }

    public AABBCollider getSelf() {
        return self;
    }

    public AABBCollider getOther() {
        return other;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public float getNormalX() {
        return normalX;
    }

    public float getNormalY() {
        return normalY;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}
