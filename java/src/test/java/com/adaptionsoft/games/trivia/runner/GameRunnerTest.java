package com.adaptionsoft.games.trivia.runner;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class GameRunnerTest {

    @Test
    public void golden_master_test() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
            GameRunner.run(ps, new Random(6));
        }
        String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);

        Approvals.verify(output);
    }
}
