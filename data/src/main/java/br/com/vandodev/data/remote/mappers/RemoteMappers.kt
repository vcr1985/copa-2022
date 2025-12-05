package br.com.vandodev.data.remote.mappers

import br.com.vandodev.data.remote.dto.MatchDto
import br.com.vandodev.domain.model.Match
import br.com.vandodev.domain.model.Team
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun List<MatchDto>.toDomain(): List<Match> {
    return this.mapNotNull { dto ->
        try {
            dto.toDomainOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

private val teamAbbreviationMap = mapOf(
    "BR" to Team("Brasil", "https://raw.githubusercontent.com/digitalinnovationone/copa-2022-api/main/flags/br.svg"),
    "RS" to Team("Sérvia", "https://raw.githubusercontent.com/digitalinnovationone/copa-2022-api/main/flags/rs.svg"),
    "CH" to Team("Suíça", "https://raw.githubusercontent.com/digitalinnovationone/copa-2022-api/main/flags/ch.svg"),
    "CM" to Team("Camarões", "https://raw.githubusercontent.com/digitalinnovationone/copa-2022-api/main/flags/cm.svg"),
    "AR" to Team("Argentina", "https://raw.githubusercontent.com/digitalinnovationone/copa-2022-api/main/flags/ar.svg"),
    "FR" to Team("França", "https://raw.githubusercontent.com/digitalinnovationone/copa-2022-api/main/flags/fr.svg")
)

fun MatchDto.toDomainOrNull(): Match? {
    val teamAData = teamA?.let { teamAbbreviationMap[it] }
    val teamBData = teamB?.let { teamAbbreviationMap[it] }

    if (
        name == null ||
        stadium?.name == null ||
        date == null ||
        teamAData == null ||
        teamBData == null
    ) {
        return null
    }

    val matchId = (name + date).hashCode()
    // CORREÇÃO FINAL: Usando o formatador correto que entende o 'Z' de UTC.
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val matchDate = LocalDateTime.parse(date, formatter)

    return Match(
        id = matchId,
        name = name,
        teamA = teamAData,
        teamB = teamBData,
        stadium = stadium.name,
        date = matchDate,
        notificationEnabled = false
    )
}
