package com.andbois.bomberman.game.entities.components;

import com.andbois.bomberman.engine.entities.Button;
import com.andbois.bomberman.engine.entities.components.AABBCollider;
import com.andbois.bomberman.engine.entities.components.Component;
import com.andbois.bomberman.engine.entities.components.Transform;
import com.badlogic.gdx.Gdx;

public class PlayerController extends Component {

    private Transform transform;
    private AABBCollider collider;

    private Button btnLeft;
    private Button btnRight;
    private Button btnUp;
    private Button btnDown;

    private float speed = 500;

    public PlayerController(Button btnLeft, Button btnRight, Button btnUp, Button btnDown) {
        this.btnLeft = btnLeft;
        this.btnRight = btnRight;
        this.btnUp = btnUp;
        this.btnDown = btnDown;
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

        if(transform.getX() < 0)
            transform.setX(0);
        if(transform.getY() < 0)
            transform.setY(0);
        if(transform.getX() > Gdx.graphics.getWidth() - collider.getWidth())
            transform.setX(Gdx.graphics.getWidth() - collider.getWidth());
        if(transform.getY() > Gdx.graphics.getHeight() - collider.getHeight())
            transform.setY(Gdx.graphics.getHeight() - collider.getHeight());
    }

    @Override
    public void onEnd() {

    }
}
