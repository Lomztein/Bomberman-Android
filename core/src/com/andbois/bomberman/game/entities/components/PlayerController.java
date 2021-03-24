package com.andbois.bomberman.game.entities.components;

import com.andbois.bomberman.engine.entities.components.UIButton;
import com.andbois.bomberman.engine.entities.Entity;
import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.engine.entities.components.Component;
import com.andbois.bomberman.engine.entities.components.Sprite;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.badlogic.gdx.graphics.Texture;

public class PlayerController extends Component {

    private Transform transform;
    private AABBCollider collider;
    private long lastBombSpawn = System.currentTimeMillis();

    private UIButton btnLeft;
    private UIButton btnRight;
    private UIButton btnUp;
    private UIButton btnDown;
    private UIButton btnBomb;

    private float speed = 5;

    public PlayerController(UIButton btnLeft, UIButton btnRight, UIButton btnUp, UIButton btnDown, UIButton btnBomb) {
        this.btnLeft = btnLeft;
        this.btnRight = btnRight;
        this.btnUp = btnUp;
        this.btnDown = btnDown;
        this.btnBomb = btnBomb;
    }

    @Override
    public void onInit() {
        transform = getEntity().getComponent(Transform.class);
        collider = getEntity().getComponent(AABBCollider.class);
    }

    @Override
    public void onTick(float deltaTime) {
        // --- Player movement --- //
        if(btnLeft.getIsClicked()) {
            transform.setX(transform.getX() - speed * deltaTime);
        }
        if(btnRight.getIsClicked()) {
            transform.setX(transform.getX() + speed * deltaTime);
        }
        if(btnUp.getIsClicked()) {
            transform.setY(transform.getY() - speed * deltaTime);
        }
        if(btnDown.getIsClicked()) {
            transform.setY(transform.getY() + speed * deltaTime);
        }

        // --- Bomb logic --- ///
        if(btnBomb.getIsClicked()) {
            if (System.currentTimeMillis() - lastBombSpawn > 1000) {
                Bomb bomb = new Bomb("texture_explosion.png", 3000);
                AABBCollider bombCol = new AABBCollider(1, 1);
                entity.getLevel().addEntity(
                        entity.getLevel().makeEntity(
                                new Transform(entity.getComponent(Transform.class).getX(), entity.getComponent(Transform.class).getY(), 0),
                                bomb, bombCol, new Sprite(new Texture("texture_bomb.png"), 1, 1)));

                lastBombSpawn = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onEnd() {

    }
}
