package com.andbois.bomberman.engine.entities.components;

import com.andbois.bomberman.engine.physics.CollisionEvent;

public class AABBCollider extends Component {

    private static float epsilon = 0.001f;

    private float width;
    private float height;

    public AABBCollider(float width, float height) {
        this.width = width;
        this.height = height;
    }

    private Transform transform;

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    // I don't really fully understand this, shamefully stolen from here https://noonat.github.io/intersect/#aabb-vs-aabb
    public CollisionEvent intersectAABB (AABBCollider other) {
        float dx = other.transform.getX() - transform.getX();
        float px = (other.width / 2 + width / 2) - Math.abs(dx);
        if (px <= 0) {
            return null;
        }

        float dy = other.transform.getY() - transform.getY();
        float py = (other.height / 2 + height / 2) - Math.abs(dy);
        if (py <= 0) {
            return null;
        }

        if (px < py) {
            float sx = Math.signum(dx);
            return new CollisionEvent(entity, this, other,
                    px * sx, 0,
                    sx,0,
                    transform.getX() + ((width / 2f) * sx),
                    other.transform.getY());
        }else{
            float sy = Math.signum(dy);
            return new CollisionEvent(entity, this, other,
                    0, py * sy,
                    0, sy,
                    other.transform.getX(),
                    transform.getY() + ((height / 2f) * sy));
        }
    }

    public void moveToContactHorizontal (AABBCollider other) {
        if (transform.getX() > other.transform.getX()) {
            transform.setX(other.transform.getX() + other.width + epsilon);
        }else{
            transform.setX(other.transform.getX() - other.width - epsilon);
        }
    }

    public void moveToContactVertical (AABBCollider other) {
        if (transform.getY() > other.transform.getY()) {
            transform.setY(other.transform.getY() + other.height + epsilon);
        }else{
            transform.setY(other.transform.getY() - other.height - epsilon);
        }
    }

    @Override
    public void onInit() {
        transform = entity.getComponent(Transform.class);
    }

    @Override
    public void onTick(float deltaTime) {
    }

    @Override
    public void onEnd() {
    }
}
