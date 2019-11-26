package com.adaptionsoft.games.trivia;

public class Player {
    private final String name;
    private int place = 0;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int place() {
        return place;
    }

    public void moveTo(int newPlace) {
        this.place = newPlace;
    }
}
