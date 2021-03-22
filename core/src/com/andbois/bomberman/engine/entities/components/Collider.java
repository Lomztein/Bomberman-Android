package com.andbois.bomberman.engine.entities.components;

public class Collider extends Component {

    private static float epsilon = 0.001f;

    private float width;
    private float height;

    public Collider(float width, float height) {
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

    public boolean collidesWith (Collider other) {
        return
                transform.getX() < other.transform.getX() + other.width + epsilon &&
                transform.getX() + width + epsilon > other.transform.getX() &&

                transform.getY() < other.transform.getY() + other.height + epsilon &&
                transform.getY() + height + epsilon  > other.transform.getY();
    }

    public void moveToContactHorizontal (Collider other) {
        if (transform.getX() > other.transform.getX()) {
            transform.setX(other.transform.getX() + other.width + epsilon);
        }else{
            transform.setX(other.transform.getX() - other.width - epsilon);
        }
    }

    public void moveToContactVertical (Collider other) {
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

    @Override
    public String toString() {
        return "Collider{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
