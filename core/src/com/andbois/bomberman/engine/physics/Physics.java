package com.andbois.bomberman.engine.physics;

import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.game.Game;

import java.util.ArrayList;

public class Physics {

    private ArrayList<AABBCollider> colliders;
    private Game game;

    public Physics(Game game) {
        this.game = game;
        colliders = new ArrayList<>();
    }

    public void addCollider (AABBCollider col) {
        colliders.add(col);
    }

    public void removeCollider (AABBCollider col) {
        colliders.remove(col);
    }

    public void checkCollisions () {
        int size = colliders.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j)
                    continue;

                AABBCollider lhs = colliders.get(i);
                AABBCollider rhs = colliders.get(j);

                CollisionEvent event = lhs.intersectAABB(rhs);
                if (event != null) {
                    game.addEvent(event);
                }
            }
        }
    }
}
