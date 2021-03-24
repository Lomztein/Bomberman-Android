package com.andbois.bomberman.game.world;

import com.andbois.bomberman.engine.data.Position;

public class Grid {

    private static final int GRID_SCALE = 32;

    private int sizeX;
    private int sizeY;
    private float scale;
    private GridPoint offset;
    private GridPoint[][] points;

    public Grid(int sizeX, int sizeY, GridPoint offset) {
        this.sizeX = sizeX / GRID_SCALE;
        this.sizeY = sizeY / GRID_SCALE;
        this.scale = GRID_SCALE;
        this.offset = offset;
    }

    public Position snap(Position position) {
        float x = (float) (Math.floor(position.getX() / scale) * scale);
        float y = (float) (Math.floor(position.getY() / scale) * scale);
        return new Position(x, y);
    }

    private void cachePoints() {
        points = new GridPoint[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                points[x][y] = new GridPoint(x, y);
            }
        }
    }

    public GridPoint[][] getPoints() {
        if (points == null) {
            cachePoints();
        }
        return points;
    }

    public int getWidth() {
        return sizeX;
    }

    public int getHeight() {
        return sizeY;
    }
}
