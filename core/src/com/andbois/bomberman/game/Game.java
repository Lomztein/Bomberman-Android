package com.andbois.bomberman.game;

import com.andbois.bomberman.engine.Input;
import com.andbois.bomberman.engine.Touch;
import com.andbois.bomberman.engine.entities.Button;
import com.andbois.bomberman.engine.entities.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ArrayList<Entity> entities;

	Button button;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		entities = new ArrayList<>();
		setup();
	}

	public void setup () {
		// Set up game here.
		button = new Button("the_button.png", 300, 300);
		addEntity(new Entity(button));
	}

	public void addEntity (Entity entity) {
		entities.add(entity);
	}

	public void removeEntity (Entity entity) {
		entities.remove(entity);
	}

	@Override
	public void render () {

		for (Entity entity : entities) {
			entity.tick(Gdx.graphics.getDeltaTime());
		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		for (Entity entity : entities) {
			entity.render(batch);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
