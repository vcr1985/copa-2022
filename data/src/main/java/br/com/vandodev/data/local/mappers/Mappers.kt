package br.com.vandodev.data.local.mappers

import br.com.vandodev.data.local.model.MatchEntity
import br.com.vandodev.data.local.model.TeamEntity
import br.com.vandodev.domain.model.Match
import br.com.vandodev.domain.model.Team
import java.time.LocalDateTime

fun MatchEntity.toDomain() = Match(
    id = id,
    name = name,
    teamA = teamA.toDomain(),
    teamB = teamB.toDomain(),
    notificationEnabled = notificationEnabled,
    stadium = stadium,
    date = LocalDateTime.parse(date)
)

fun TeamEntity.toDomain() = Team(
    name = name,
    flag = flag
)

fun Match.toEntity() = MatchEntity(
    id = id,
    name = name,
    teamA = teamA.toEntity(),
    teamB = teamB.toEntity(),
    notificationEnabled = notificationEnabled,
    stadium = stadium,
    date = date.toString()
)

fun Team.toEntity() = TeamEntity(
    name = name,
    flag = flag
)
