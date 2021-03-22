package com.andbois.bomberman.engine.physics;

import com.andbois.bomberman.engine.Event;
import com.andbois.bomberman.engine.entities.components.Collider;

public class CollisionEvent extends Event {

    private Collider rhs;
    private Collider lhs;

    public CollisionEvent(Object owner, Collider lhs, Collider rhs) {
        super(owner);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Collider getLhs() {
        return lhs;
    }

    public Collider getRhs() {
        return rhs;
    }

    @Override
    public String toString() {
        return "CollisionEvent{" +
                "rhs=" + rhs +
                ", lhs=" + lhs +
                '}';
    }
}
