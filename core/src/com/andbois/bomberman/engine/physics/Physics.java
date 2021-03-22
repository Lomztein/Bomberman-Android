package com.andbois.bomberman.engine;

import com.andbois.bomberman.engine.entities.components.Collider;
import com.andbois.bomberman.game.Game;

import java.util.ArrayList;

public class Physics {

    private ArrayList<Collider> colliders;
    private Game game;

    public Physics(Game game) {
        this.game = game;
    }

    public void addCollider (Collider col) {
        colliders.add(col);
    }

    public void removeCollider (Collider col) {
        colliders.remove(col);
    }
}
