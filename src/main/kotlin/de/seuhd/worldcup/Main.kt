package de.seuhd.worldcup
import kotlinx.serialization.json.Json

fun main() {
    //TODO: Load JSON data
    val jsonText = object {}.javaClass
        .getResourceAsStream("/world_cup_2026_full_data.json")
        ?.bufferedReader()
        ?.use { it.readText() }
        ?: error("Could not load world_cup_2026_full_data.json")

    // convert the JSON text into Kotlin objects
    val worldCupData = Json.decodeFromString<WorldCupData>(jsonText)

    //TODO: Implement interactive menu
    while (true) {
        println()
        println("======= FIFA World Cup 2026 : Betting Console =======")
        println("1. Show Standings")
        println("2. Show Matches")
        println("3. Place Bets")
        println("4. Show Betting Score")
        println("5. Exit")
        println("=====================================================")
        print("Choose an option (1 to 5): ")

        when (readln()) {
            "1" -> showStandings(worldCupData.groups)
            "2" -> showMatches(worldCupData.groups)
            "3" -> placeBets(worldCupData.groups)
            "4" -> showBettingScore(worldCupData.groups)
            "5" -> {
                println("Thank you. Best of luck!!")
                return
            }
            else -> println("!!Invalid input!! Please enter a valid option")
        }
    }

}

/* -------------------------------------------------------------
   1) Show Standings
   ------------------------------------------------------------- */
data class Standing(
    val teamId: String,
    val teamName: String,
    var played: Int = 0,
    var wins: Int = 0,
    var draws: Int = 0,
    var losses: Int = 0,
    var goalsFor: Int = 0,
    var goalsAgainst: Int = 0,
    var points: Int = 0
) {
    val goalDifference: Int
        get() = goalsFor - goalsAgainst
}

private fun showStandings(allGroups: List<Group>) {
    //TODO
    println("Enter group name, for example 'Group A', or type 'all' to view the current table of groups")
    val input = readln()

    println("Legend: P = Played, W = Wins, D = Draws, L = Losses, GF = Goals For, GA = Goals Against, GD = Goal Difference, Pts = Points")

    // print all groups
    if (input.equals("all", ignoreCase = true)) {
        for (group in allGroups) {
            printGroupStanding(group)
        }
        return
    }

    // find and print a single group
    val group = allGroups.find { it.name.equals(input, ignoreCase = true) }

    if (group == null) {
        println("Group not found.")
        return
    }

    printGroupStanding(group)
}

private fun printGroupStanding(group: Group) {
    // create empty standing rows for all teams in the group
    val standings = group.teams.associate {
        it.id to Standing(
            teamId = it.id,
            teamName = it.name,
        )
    }.toMutableMap()

    // update the standing rows using all matches that already have scores
    for (match in group.matches) {
        val homeScore = match.homeScore
        val awayScore = match.awayScore

        // skip matches that have not been played yet
        if (homeScore == null || awayScore == null) continue

        val home = standings[match.homeTeam] ?: continue
        val away = standings[match.awayTeam] ?: continue

        home.played++
        away.played++

        home.goalsFor += homeScore
        home.goalsAgainst += awayScore

        away.goalsFor += awayScore
        away.goalsAgainst += homeScore

        // add win, loss, draw, and points based on the result.
        when {
            homeScore > awayScore -> {
                home.wins++
                home.points += 3
                away.losses++
            }

            homeScore < awayScore -> {
                away.wins++
                away.points += 3
                home.losses++
            }

            else -> {
                home.draws++
                away.draws++
                home.points += 1
                away.points += 1
            }
        }
    }

    // sort by points first, then goal difference, then goals scored
    val sortedStandings = standings.values.sortedWith(
        compareByDescending<Standing> { it.points }
            .thenByDescending { it.goalDifference }
            .thenByDescending { it.goalsFor }
    )

    println()
    println(group.name)
    println("Team                 P  W  D  L  GF  GA  GD  Pts")

    // print the final table
    for (standing in sortedStandings) {
        println(
            "${standing.teamName.padEnd(20)} " +
                    "${standing.played}  " +
                    "${standing.wins}  " +
                    "${standing.draws}  " +
                    "${standing.losses}  " +
                    "${standing.goalsFor.toString().padStart(2)}  " +
                    "${standing.goalsAgainst.toString().padStart(2)}  " +
                    "${standing.goalDifference.toString().padStart(2)}  " +
                    standing.points.toString().padStart(3)
        )
    }
}

/* -------------------------------------------------------------
   2) Show Matches
   ------------------------------------------------------------- */
private fun readGroup(allGroups: List<Group>, prompt: String): Group? {
    println(prompt)
    val input = readln()

    val group = allGroups.find { it.name.equals(input, ignoreCase = true) }
    if (group == null) {
        println("Group not found.")
    }

    return group
}

private fun teamNameById(group: Group): Map<String, String> =
    group.teams.associate { it.id to it.name }

private fun showMatches(allGroups: List<Group>) {
    //TODO
    val group = readGroup(
        allGroups,
        "Which group's matches do you want to see? Example: 'Group A'"
    ) ?: return

    val teamNames = teamNameById(group)

    println()
    println("${group.name} Matches")
    println("Date         Home Team              Score     Away Team              Ground")

    for (match in group.matches) {
        val homeTeam = teamNames[match.homeTeam] ?: match.homeTeam
        val awayTeam = teamNames[match.awayTeam] ?: match.awayTeam
        val score = if (match.homeScore == null || match.awayScore == null) {
            "TBD"
        } else {
            "${match.homeScore} - ${match.awayScore}"
        }

        println(
            "${match.date.padEnd(12)} " +
                    "${homeTeam.padEnd(22)} " +
                    "${score.padEnd(9)} " +
                    "${awayTeam.padEnd(22)} " +
                    match.ground
        )
    }
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