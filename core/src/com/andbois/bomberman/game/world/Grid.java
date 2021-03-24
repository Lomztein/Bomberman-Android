package com.andbois.bomberman.game.world;

import com.andbois.bomberman.engine.data.Position;

public class Grid {

    private float scale;

    public Grid(float cellSize) {
        this.scale = cellSize;
    }

    public Position snap(Position position) {
        float x = (float) (Math.floor(position.getX() / scale) * scale);
        float y = (float) (Math.floor(position.getY() / scale) * scale);
        return new Position(x, y);
    }
}
