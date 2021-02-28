package com.andbois.bomberman.engine.entities.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Renderer extends Component {

    protected Transform transform;

    @Override
    public void onInit() {

    }

    public abstract void render (SpriteBatch batch);

}
