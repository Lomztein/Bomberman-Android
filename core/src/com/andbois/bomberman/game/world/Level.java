package com.andbois.bomberman.game.world;

import com.andbois.bomberman.game.entities.components.Bomb;
import com.andbois.bomberman.engine.entities.Button;
import com.andbois.bomberman.engine.entities.Entity;
import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.engine.entities.components.Sprite;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.andbois.bomberman.engine.physics.CollisionEvent;
import com.andbois.bomberman.game.Game;
import com.andbois.bomberman.game.entities.components.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.ListIterator;

public class Level {

    private ArrayList<Entity> entities;

    private Entity player;
    private long lastBombSpawn = System.currentTimeMillis();

    private Button btnLeft, btnRight, btnDown, btnUp, btnBomb;

    private Game game;

    public Level(Game game) {
        this.game = game;
        setup();
    }

    public void setup () {
        entities = new ArrayList<>();

        btnLeft = new Button("button_left.png", 100, 300);
        btnRight = new Button("button_right.png", 600, 300);
        btnDown = new Button("button_down.png", 350, 100);
        btnUp = new Button("button_up.png", 350, 500);
        btnBomb = new Button("button_bomb.png", Gdx.graphics.getWidth() - 300, 300);

        // Set up game here.
        AABBCollider playerCol =  new AABBCollider(200, 200);
        player = new Entity(new Transform(200, 200, 0), new PlayerController(btnLeft, btnRight, btnDown, btnUp), playerCol, new Sprite(new Texture("texture_player.png"), 200, 200));

        addEntity(player);
        game.getPhysics().addCollider(playerCol);

        Entity wall = new Entity(new Transform(500, 500, 0), new Sprite(new Texture("texture_player.png"), 200, 200), new AABBCollider(200, 200));
        game.getPhysics().addCollider(wall.getComponent(AABBCollider.class));
        addEntity(wall);

        addEntity(new Entity(btnLeft));
        addEntity(new Entity(btnRight));
        addEntity(new Entity(btnDown));
        addEntity(new Entity(btnUp));
        addEntity(new Entity(btnBomb));
    }

    public void tick (float dTime) {
        for (Entity entity : entities) {
            entity.tick(Gdx.graphics.getDeltaTime());
        }

        // --- Bomb logic --- ///
        if(btnBomb.getIsClicked()) {
            if(System.currentTimeMillis() - lastBombSpawn > 1000) {
                Bomb bomb = new Bomb("texture_bomb.png", "texture_explosion.png", (int)player.getComponent(Transform.class).getX(), (int)player.getComponent(Transform.class).getY(), 3000);
                AABBCollider bombCol =  new AABBCollider(200, 200);
                addEntity(
                        new Entity(
                                new Transform(
                                        player.getComponent(Transform.class).getX(), player.getComponent(Transform.class).getY(),
                                        0),bomb, bombCol));

                game.getPhysics().addCollider(bombCol);
                lastBombSpawn = System.currentTimeMillis();
            }
        }

        ListIterator<Entity> it = entities.listIterator();
        while(it.hasNext()) {
            Bomb bomb = it.next().getComponent(Bomb.class);
            if (bomb != null) {
                if (bomb.getShouldDispose()) {
                    it.remove();
                    game.getPhysics().removeCollider(bomb.getEntity().getComponent(AABBCollider.class));
                }
            }
        }
    }

    public void handleEvents () {
        CollisionEvent playerCollision = game.getEvent(player, CollisionEvent.class);
        if (playerCollision != null) {
            System.out.println("Player collision!");
            player.getTransform().setX(player.getTransform().getX() - playerCollision.getDeltaX());
            player.getTransform().setY(player.getTransform().getY() - playerCollision.getDeltaY());
        }
    }

    public void render (SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public void removeEntity (Entity entity) {
        entities.remove(entity);
    }

}
