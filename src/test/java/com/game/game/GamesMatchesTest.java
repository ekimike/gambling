package com.game.game;

import com.game.game.entity.GameMatches;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class GamesMatchesTest {

    private static GameMatches gameMatches;

    @BeforeAll
    public static void beforeAll() {
        gameMatches = new GameMatches();
    }

    @Test
    @DisplayName("Should return null class element")
    public void restart_user_gambling_history_returns_null() {
        assertNull(gameMatches.getRecordByPlayer());
    }


}
