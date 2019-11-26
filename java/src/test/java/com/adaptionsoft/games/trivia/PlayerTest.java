package com.adaptionsoft.games.trivia;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void create_a_new_player_with_a_name() {
        assertThat(new Player("Joe").toString()).isEqualTo("Joe");
    }

    @Test public void
    starts_at_place_0() {
        assertThat(new Player("Jim").place()).isEqualTo(0);
    }

    @Test public void
    can_move() {
        Player jim = new Player("Jim");
        jim.moveTo(4);
        assertThat(jim.place()).isEqualTo(4);
    }
}
