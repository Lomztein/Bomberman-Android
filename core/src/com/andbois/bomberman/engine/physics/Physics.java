package com.andbois.bomberman.engine.physics;

import com.andbois.bomberman.engine.entities.components.Collider;
import com.andbois.bomberman.game.Game;

import java.util.ArrayList;

public class Physics {

    private ArrayList<Collider> colliders;
    private Game game;

    public Physics(Game game) {
        this.game = game;
        colliders = new ArrayList<>();
    }

    public void addCollider (Collider col) {
        colliders.add(col);
    }

    public void removeCollider (Collider col) {
        colliders.remove(col);
    }

    public void checkCollisions () {
        int size = colliders.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j)
                    continue;

                Collider lhs = colliders.get(i);
                Collider rhs = colliders.get(j);

                if (lhs.collidesWith(rhs)) {
                    game.addEvent(new CollisionEvent(lhs.getEntity(), lhs, rhs));
                }
            }
        }
    }

    public void handleCollisions () {
        int size = colliders.size();
        for (int i = 0; i < size; i++) {
            Collider col = colliders.get(i);
            CollisionEvent event = game.getEvent(col.getEntity(), CollisionEvent.class);
            if (event != null) {
                event.getLhs().getEntity().onCollision(event.getRhs());
            }
        }
    }
}
