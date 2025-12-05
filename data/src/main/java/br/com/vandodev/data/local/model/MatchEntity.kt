package br.com.vandodev.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @Embedded(prefix = "team_a_")
    val teamA: TeamEntity,
    @Embedded(prefix = "team_b_")
    val teamB: TeamEntity,
    val notificationEnabled: Boolean,
    val stadium: String,
    val date: String
)

data class TeamEntity(
    val name: String,
    val flag: String
)
