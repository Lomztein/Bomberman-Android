package com.andbois.bomberman.game;

import com.andbois.bomberman.engine.Event;
import com.andbois.bomberman.engine.entities.components.Collider;
import com.andbois.bomberman.engine.entities.components.PlayerController;
import com.andbois.bomberman.engine.entities.components.Sprite;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.andbois.bomberman.engine.physics.CollisionEvent;
import com.andbois.bomberman.engine.physics.Physics;
import com.andbois.bomberman.engine.entities.Bomb;
import com.andbois.bomberman.engine.entities.Button;
import com.andbois.bomberman.engine.entities.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.ListIterator;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private ArrayList<Entity> entities;

	private Entity player;
	private long lastBombSpawn = System.currentTimeMillis();

	private Button btnLeft, btnRight, btnDown, btnUp, btnBomb;

	private Physics physics;
	private ArrayList<Event> events;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		entities = new ArrayList<>();
		physics = new Physics(this);
		events = new ArrayList<>();
		setup();
	}

	public void setup () {

		btnLeft = new Button("button_left.png", 100, 300);
		btnRight = new Button("button_right.png", 600, 300);
		btnDown = new Button("button_down.png", 350, 100);
		btnUp = new Button("button_up.png", 350, 500);
		btnBomb = new Button("button_bomb.png", Gdx.graphics.getWidth() - 300, 300);

		// Set up game here.
		Collider playerCol =  new Collider(200, 200);
		player = new Entity(new Transform (200, 200, 0), new PlayerController(btnLeft, btnRight, btnDown, btnUp), playerCol, new Sprite(new Texture("texture_player.png"), 200, 200));

		addEntity(player);
		physics.addCollider(playerCol);

		Entity wall = new Entity(new Transform(500, 500, 0), new Sprite(new Texture("texture_player.png"), 200, 200), new Collider(200, 200));
		physics.addCollider(wall.getComponent(Collider.class));
		addEntity(wall);

		addEntity(new Entity(btnLeft));
		addEntity(new Entity(btnRight));
		addEntity(new Entity(btnDown));
		addEntity(new Entity(btnUp));
		addEntity(new Entity(btnBomb));
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


		// --- Bomb logic --- ///
		if(btnBomb.getIsClicked()) {
			if(System.currentTimeMillis() - lastBombSpawn > 1000) {
				Bomb bomb = new Bomb("texture_bomb.png", "texture_explosion.png", (int)player.getComponent(Transform.class).getX(), (int)player.getComponent(Transform.class).getY(), 3000);
				Collider bombCol =  new Collider(200, 200);
				addEntity(
						new Entity(
								new Transform(
								player.getComponent(Transform.class).getX(), player.getComponent(Transform.class).getY(),
								0),bomb, bombCol));

				physics.addCollider(bombCol);
				lastBombSpawn = System.currentTimeMillis();
			}
		}

		ListIterator<Entity> it = entities.listIterator();
		while(it.hasNext()) {
			Bomb bomb = it.next().getComponent(Bomb.class);
			if (bomb != null) {
				if (bomb.getShouldDispose()) {
					it.remove();
					physics.removeCollider(bomb.getEntity().getComponent(Collider.class));
				}
			}
		}
	}

	private void postTick () {
		CollisionEvent playerCollision = getEvent(player, CollisionEvent.class);
		if (playerCollision != null) {
			System.out.println("Player collision!");
			Collider playerCollider = player.getComponent(Collider.class);
			if (btnLeft.getIsClicked()) {
				playerCollider.moveToContactHorizontal(playerCollision.getRhs());
			}
			if (btnRight.getIsClicked()) {
				playerCollider.moveToContactHorizontal(playerCollision.getRhs());
			}
			if (btnUp.getIsClicked()) {
				playerCollider.moveToContactVertical(playerCollision.getRhs());
			}
			if (btnDown.getIsClicked()) {
				playerCollider.moveToContactVertical(playerCollision.getRhs());
			}
		}
	}

	@Override
	public void render () {

		tick();
		physics.checkCollisions();
		postTick();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		for (Entity entity : entities) {
			entity.render(batch);
		}

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
