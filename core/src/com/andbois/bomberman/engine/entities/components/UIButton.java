package com.andbois.bomberman.engine.entities.components;

import com.andbois.bomberman.engine.Input;
import com.andbois.bomberman.engine.Touch;
import com.andbois.bomberman.engine.entities.components.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class UIButton extends Component {

    private Rectangle rectangle;

    private Boolean isClicked = false;

    public UIButton(int posX, int posY, int width, int height) {
        rectangle = new Rectangle(posX, posY, width, height);
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onTick(float deltaTime) {
        isClicked = false;

        Touch[] touches = Input.getTouches();
        for (Touch touch : touches) {
            int x = touch.getX();
            int y = Gdx.graphics.getHeight() - 1 - touch.getY();

            if(rectangle.contains(x, y)) {
                isClicked = true;
            }
        }
    }

    @Override
    public void onEnd() {

    }

    public Boolean getIsClicked() {
        return isClicked;
    }
}
