package com.andbois.bomberman.engine.entities;

import com.andbois.bomberman.engine.Input;
import com.andbois.bomberman.engine.Touch;
import com.andbois.bomberman.engine.entities.components.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button extends Renderer {
    private Texture texture;
    private Rectangle rectangle;

    public Button(String textureName, int posX, int posY) {
        texture = new Texture(textureName);
        rectangle = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void onInit() {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, rectangle.getX() - (texture.getWidth() / 2), rectangle.getY() - (texture.getHeight() / 2));
    }

    @Override
    public void onTick(float deltaTime) {
        Touch[] touches = Input.getTouches();
        for (Touch touch : touches) {
            int x = touch.getX();
            int y = Gdx.graphics.getHeight() - 1 - touch.getY();

            if(rectangle.contains(x, y)) {
                System.out.println("clicked!");
            }
        }
    }

    @Override
    public void onEnd() {

    }
}
