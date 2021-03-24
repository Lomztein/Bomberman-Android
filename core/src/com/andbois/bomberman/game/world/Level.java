package com.andbois.bomberman.game.world;

import com.andbois.bomberman.game.entities.components.Bomb;
import com.andbois.bomberman.engine.entities.Button;
import com.andbois.bomberman.engine.entities.Entity;
import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.engine.entities.components.Component;
import com.andbois.bomberman.engine.entities.components.Sprite;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.andbois.bomberman.engine.physics.CollisionEvent;
import com.andbois.bomberman.game.Game;
import com.andbois.bomberman.game.entities.components.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level {

    private ArrayList<Entity> entities;

    private Queue<Entity> toAdd;
    private Queue<Entity> toRemove;

    private Entity player;

    private Game game;

    public Level(Game game) {
        this.game = game;
    }

    public void setup () {
        entities = new ArrayList<>();
        toAdd = new LinkedList<>();
        toRemove = new LinkedList<>();

        makePlayer();

        Entity wall = makeEntity(new Transform(5, 5, 0), new Sprite(new Texture("texture_player.png"), 1, 1), new AABBCollider(1, 1));
        addEntity(wall);
    }

    private void makePlayer () {
        Button btnLeft = new Button("button_left.png", 100, 300);
        Button btnRight = new Button("button_right.png", 600, 300);
        Button btnDown = new Button("button_down.png", 350, 100);
        Button btnUp = new Button("button_up.png", 350, 500);
        Button btnBomb = new Button("button_bomb.png", Gdx.graphics.getWidth() - 300, 300);

        addEntity(makeEntity(btnLeft));
        addEntity(makeEntity(btnRight));
        addEntity(makeEntity(btnDown));
        addEntity(makeEntity(btnUp));
        addEntity(makeEntity(btnBomb));

        AABBCollider playerCol =  new AABBCollider(1, 1);
        player = makeEntity(new Transform(0, 0, 0), new PlayerController(btnLeft, btnRight, btnDown, btnUp, btnBomb), playerCol, new Sprite(new Texture("texture_player.png"), 1, 1));
        addEntity(player);
    }

    public void tick (float dTime) {
        for (Entity entity : entities) {
            entity.tick(Gdx.graphics.getDeltaTime());
        }

        while(toAdd.size() != 0) {
            internalAddEntity(toAdd.poll());
        }

        while (toRemove.size() != 0) {
            internalRemoveEntity(toRemove.poll());
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

    public Entity makeEntity (Component... components) {
        return new Entity(this, components);
    }

    public void addEntity(Component... entityComponents) {
        addEntity(makeEntity(entityComponents));
    }

    public void addEntity (Entity entity) {
        toAdd.add(entity);
    }

    public void removeEntity(Entity entity) {
        toRemove.add(entity);
    }

    private void internalAddEntity (Entity entity) {
        entities.add(entity);
        AABBCollider collider = entity.getComponent(AABBCollider.class);
        if (collider != null) {
            game.getPhysics().addCollider(collider);
        }
    }

    private void internalRemoveEntity (Entity entity) {
        entities.remove(entity);
        AABBCollider collider = entity.getComponent(AABBCollider.class);
        if (collider != null) {
            game.getPhysics().removeCollider(collider);
        }
    }

}
