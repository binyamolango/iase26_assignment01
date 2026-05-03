package de.seuhd.worldcup

import kotlinx.serialization.Serializable

@Serializable
data class WorldCupData(
    val tournament: String,
    val groups: List<Group>,
    val knockouts: List<Knockout>
)

@Serializable
data class Group(
   //TODO
    val name: String,
    val teams: List<Team>,
    val matches: List<Match>
)

@Serializable
data class Team(
    //TODO
    val id: String,
    val name: String
)

@Serializable
data class Match(
    //TODO
    val matchId: Int,
    val round: String,
    val date: String,
    val homeTeam: String,
    val homeScore: Int?,
    val awayScore: Int?,
    val groung: String
)

@Serializable
data class Knockout(
    //TODO
    val matchId: Int,
    val round: String,
    val date: String,
    val homePlaceholder: String,
    val awayPlaceholder: String,
    val homeScroe: Int?,
    val awayScore: Int?,
    val ground: String
)