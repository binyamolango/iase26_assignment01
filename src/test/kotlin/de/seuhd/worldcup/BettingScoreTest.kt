package de.seuhd.worldcup

import kotlin.test.*

class BettingScoreTest {

    @Test
    fun `detect match outcome for betting score`() {
        assertEquals(MatchOutcome.HOME_WIN, matchOutcome(2, 1))
        assertEquals(MatchOutcome.AWAY_WIN, matchOutcome(0, 3))
        assertEquals(MatchOutcome.DRAW, matchOutcome(1, 1))
        assertNull(matchOutcome(null, 1))
        assertNull(matchOutcome(1, null))
    }
}