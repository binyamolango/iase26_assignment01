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
    while (true) {
        println()
        println("======= World Cup 2026 Menu =======")
        println("1. Show Standings")
        println("2. Show Matches")
        println("3. Place Bets")
        println("4. Show Betting Score")
        println("0. Exit")
        print("Choose an option: ")

        when (readln()) {
            "1" -> showStandings(worldCupData.groups)
            "2" -> showMatches(worldCupData.groups)
            "3" -> placeBets(worldCupData.groups)
            "4" -> showBettingScore(worldCupData.groups)
            "0" -> {
                println("Thank you. Best of luck!!")
                return
            }
            else -> println("Invalid Option. Please enter a valid option")
        }
    }

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