package de.seuhd.worldcup

import kotlin.test.*

class StandingTest {

    @Test
    fun `calculate goal difference for standing`() {
        val standing = Standing(
            teamId = "A",
            teamName = "Alpha",
            goalsFor = 5,
            goalsAgainst = 2
        )

        assertEquals(3, standing.goalDifference)
    }
}
