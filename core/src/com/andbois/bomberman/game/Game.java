package com.andbois.bomberman.game;

import com.andbois.bomberman.engine.Input;
import com.andbois.bomberman.engine.Touch;
import com.andbois.bomberman.engine.entities.Button;
import com.andbois.bomberman.engine.entities.Entity;
import com.andbois.bomberman.engine.entities.Player;
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

	Player player;
	Button btnLeft, btnRight, btnDown, btnUp;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		entities = new ArrayList<>();
		setup();
	}

	public void setup () {
		// Set up game here.
		player = new Player("badlogic.jpg", 200, 800);
		addEntity(new Entity(player));

		btnLeft = new Button("button_left.png", 100, 300);
		btnRight = new Button("button_right.png", 600, 300);
		btnDown = new Button("button_down.png", 350, 100);
		btnUp = new Button("button_up.png", 350, 500);

		addEntity(new Entity(btnLeft));
		addEntity(new Entity(btnRight));
		addEntity(new Entity(btnDown));
		addEntity(new Entity(btnUp));
	}

	public void addEntity (Entity entity) {
		entities.add(entity);
	}

	public void removeEntity (Entity entity) {
		entities.remove(entity);
	}

	public void tick () {
		for (Entity entity : entities) {
			entity.tick(Gdx.graphics.getDeltaTime());
		}

		if(btnLeft.getIsClicked()) {
			player.setX(player.getX() - 10);
		}
		if(btnRight.getIsClicked()) {
			player.setX(player.getX() + 10);
		}
		if(btnUp.getIsClicked()) {
			player.setY(player.getY() + 10);
		}
		if(btnDown.getIsClicked()) {
			player.setY(player.getY() - 10);
		}

		if(player.getX() < 0)
			player.setX(0);
		if(player.getY() < 0)
			player.setY(0);
		if(player.getX() > Gdx.graphics.getWidth() - player.getWidth())
			player.setX(Gdx.graphics.getWidth() - player.getWidth());
		if(player.getY() > Gdx.graphics.getHeight() - player.getHeight())
			player.setY(Gdx.graphics.getHeight() - player.getHeight());
	}

	@Override
	public void render () {
		tick();

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
