package de.seuhd.worldcup
import kotlinx.serialization.json.Json

fun main() {
    //TODO: Load JSON data
    val jsonText = object {}.javaClass
        .getResourceAsStream("/world_cup_2026_full_data.json")
        ?.bufferedReader()
        ?.use { it.readText() }
        ?: error("Could not load world_cup_2026_full_data.json")

    val worldCupData = Json.decodeFromString<WorldCupData>(jsonText)

    //TODO: Implement interactive menu
}

/* -------------------------------------------------------------
   1) Show Standings
   ------------------------------------------------------------- */
private fun showStandings(allGroups: List<Group>) {
    //TODO
}

/* -------------------------------------------------------------
   2) Show Matches
   ------------------------------------------------------------- */
private fun showMatches(allGroups: List<Group>) {
    //TODO
}

/* -------------------------------------------------------------
   3) Place Bets
   ------------------------------------------------------------- */
private fun placeBets(allGroups: List<Group>) {
    //TODO
}

/* -------------------------------------------------------------
   4) Show Betting Score
   ------------------------------------------------------------- */
private fun showBettingScore(allGroups: List<Group>) {
    //TODO
}