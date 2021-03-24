package com.andbois.bomberman.game.world;

import com.andbois.bomberman.engine.entities.components.UISprite;
import com.andbois.bomberman.engine.entities.components.UIButton;
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

        Entity wall = makeEntity(new Transform(5, 5, 0), new Sprite(new Texture("texture_player.png"), 2, 2), new AABBCollider(1, 1));
        addEntity(wall);
    }

    private void makePlayer () {
        UIButton btnLeft = new UIButton(100, 300, 100, 100);
        UIButton btnRight = new UIButton(600, 300, 100, 100);
        UIButton btnDown = new UIButton(350, 100, 100, 100);
        UIButton btnUp = new UIButton(350, 500, 100, 100);
        UIButton btnBomb = new UIButton(Gdx.graphics.getWidth() - 300, 300, 100, 100);

        addEntity(btnLeft, new UISprite(new Texture("button_left.png"), 100, Gdx.graphics.getHeight () - 300, 100, 100));
        addEntity(btnRight, new UISprite(new Texture("button_right.png"), 600, Gdx.graphics.getHeight () - 300, 100, 100));
        addEntity(btnDown, new UISprite(new Texture("button_down.png"), 350, Gdx.graphics.getHeight () - 100, 100, 100));
        addEntity(btnUp, new UISprite(new Texture("button_up.png"), 350, Gdx.graphics.getHeight () - 500, 100, 100));
        addEntity(btnBomb, new UISprite(new Texture("button_bomb.png"), Gdx.graphics.getWidth() - 300,Gdx.graphics.getHeight () -  300, 100, 100));

        AABBCollider playerCol =  new AABBCollider(1, 1);
        player = makeEntity(new Transform(0, 0, 0), new PlayerController(btnLeft, btnRight, btnDown, btnUp, btnBomb), playerCol, new Sprite(new Texture("texture_player.png"), 1, 1));
        addEntity(player);
    }

    public void tick (float dTime) {
        for (Entity entity : entities) {
            entity.tick(Gdx.graphics.getDeltaTime());
        }

        game.setCameraPosition(player.getTransform().getX(), player.getTransform().getY());
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

    public Game getGame() {
        return game;
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
