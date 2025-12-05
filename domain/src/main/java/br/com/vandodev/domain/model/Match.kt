package br.com.vandodev.domain.model

import java.time.LocalDateTime

data class Match(
    val id: Int,
    val name: String,
    val teamA: Team,
    val teamB: Team,
    val notificationEnabled: Boolean = false,
    val stadium: String,
    val date: LocalDateTime
)