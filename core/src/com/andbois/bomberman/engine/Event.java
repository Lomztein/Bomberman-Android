package com.andbois.bomberman.engine;

public class Event {

    private Object owner;

    public Event(Object owner) {
        this.owner = owner;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
