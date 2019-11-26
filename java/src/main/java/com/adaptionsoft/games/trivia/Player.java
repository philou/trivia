package com.adaptionsoft.games.trivia;

public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int place() {
        return 0;
    }
}
