package com.andbois.bomberman.game.world;

import com.andbois.bomberman.engine.entities.Entity;
import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.engine.entities.components.Sprite;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.andbois.bomberman.game.entities.components.Wall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class LevelLoader {

    private Color wall = new Color(0x000000FF);
    private Color crate = new Color(0x7F3300FF);
    private Color player = new Color(0x00FF21FF);
    private float margin = 0.001f;

    public Entity loadOnto (Texture texture, Level level) {
        texture.getTextureData().prepare();
        Pixmap pixels = texture.getTextureData().consumePixmap();
        Entity lastPlayer = null;
        for (int y = 0; y < pixels.getHeight(); y++) {
            for (int x = 0; x < pixels.getWidth(); x++) {
                int pixel = pixels.getPixel(x, y);
                Color color = new Color(pixel);

                if (colorEquals(color, wall)) {
                    level.addEntity(makeWall(level, x, y));
                }

                if (colorEquals(color, crate)) {
                    level.addEntity(makeCrate(level, x, y));
                }

                if (colorEquals(color, player)) {
                    lastPlayer = makePlayer(level, x, y);
                    level.addEntity(lastPlayer);
                }
            }
        }

        return lastPlayer;
    }

    private float calcColorDistance (Color lhs, Color rhs) {
        float r1 = lhs.r;
        float r2 = rhs.r;

        float g1 = lhs.g;
        float g2 = rhs.g;

        float b1 = lhs.b;
        float b2 = rhs.b;

        float a1 = lhs.a;
        float a2 = rhs.a;

        return (float)(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2) + Math.pow(a2 - a1, 2));
    }

    private boolean colorEquals (Color lhs, Color rhs) {
        return calcColorDistance(lhs, rhs) < margin;
    }

    private Entity makeWall (Level level, int x, int y) {
        return level.makeEntity(new Transform(x, y, 0), new Sprite(new Texture("tex_floor.png"), 1, 1), new AABBCollider(1, 1), new Wall());
    }

    private Entity makeCrate (Level level, int x, int y) {
        return level.makeEntity(new Transform(x, y, 0), new Sprite(new Texture("tex_crate.png"), 1, 1), new AABBCollider(1, 1));
    }

    private Entity makePlayer (Level level, int x, int y) {
        return level.makeEntity(new Transform(x, y, 0), new Sprite(new Texture("texture_player.png"), 1, 1), new AABBCollider(1, 1));
    }
}
