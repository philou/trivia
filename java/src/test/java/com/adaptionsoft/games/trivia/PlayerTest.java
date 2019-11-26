package com.adaptionsoft.games.trivia;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void create_a_new_player_with_a_name() {
        assertThat(new Player("Joe").toString()).isEqualTo("Joe");
    }
}
