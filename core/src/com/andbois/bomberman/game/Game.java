package com.andbois.bomberman.game;

import com.andbois.bomberman.engine.Event;
import com.andbois.bomberman.engine.physics.Physics;
import com.andbois.bomberman.engine.entities.Entity;
import com.andbois.bomberman.game.world.Level;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;

	private Physics physics;
	private Level currentLevel;

	private ArrayList<Event> events;

	@Override
	public void create () {
		batch = new SpriteBatch();
		physics = new Physics(this);
		events = new ArrayList<>();
		setup();
	}

	public void setup () {
		currentLevel = new Level(this);
	}

	public void tick () {
		currentLevel.tick(Gdx.graphics.getDeltaTime());
	}

	private void handleEvents() {
		currentLevel.handleEvents();
	}

	public Physics getPhysics() {
		return physics;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	@Override
	public void render () {

		tick();
		physics.checkCollisions();
		handleEvents();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		currentLevel.render(batch);
		batch.end();
		events.clear(); // Clear all events at the start of a new frame.
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void addEvent(Event event) {
		events.add(event);
	}

	public void removeEvent(Event event) {
		events.remove(event);
	}

	public <T extends Event> T getEvent(Object owner, Class<T> t) {
		Event event = null;
		for (Event e : events) {
			if (e.getOwner() == owner && t.isAssignableFrom(e.getClass())) {
				event = e;
				break;
			}
		}

		return (T)event;
	}
}
