package com.andbois.bomberman.engine.entities;

import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.engine.entities.components.Component;
import com.andbois.bomberman.engine.entities.components.Renderer;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.andbois.bomberman.game.world.Level;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Entity {

    private Transform transform;
    private ArrayList<Component> components;
    private ArrayList<Renderer> renderers;

    private Level level;

    public Entity (Level level, Component... components) {
        this.components = new ArrayList<>();
        this.renderers = new ArrayList<>();
        this.level = level;

        for (Component component : components) {
            internalAddComponent(component, false);
        }

        for (Component component : this.components) {
            component.setEntity(this);
            component.onInit();
        }
    }

    public Level getLevel () {
        return level;
    }

    public <T extends Component> T getComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isAssignableFrom(component.getClass())) {
                return (T) component;
            }
        }
        return null;
    }

    public void addComponent (Component component) {
        internalAddComponent(component, true);
    }

    private void internalAddComponent(Component component, boolean init) {
        components.add(component);
        if (component instanceof Renderer) {
            renderers.add((Renderer)component);
        }

        if (component instanceof Transform){
            transform = (Transform)component;
        }

        if (init) {
            component.setEntity(this);
            component.onInit();
        }
    }

    public void removeComponent (Component component) {
        components.remove(component);
        renderers.remove(component);
        component.setEntity(null);
        component.onEnd();
    }

    public void tick (float deltaTime) {
        for (Component component : components) {
            component.onTick(deltaTime);
        }
    }

    public void render (SpriteBatch batch) {
        for (Renderer renderer : renderers) {
            renderer.render(batch);
        }
    }

    public void onCollision (AABBCollider other) {
        for (Component component : components) {
            component.onCollision(other);
        }
    }

    public Transform getTransform() {
        return transform;
    }
}
