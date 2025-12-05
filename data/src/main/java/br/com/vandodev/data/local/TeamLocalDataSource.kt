package br.com.vandodev.data.local

import br.com.vandodev.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface TeamLocalDataSource {
    fun getMatches(): Flow<List<Match>>
    suspend fun upsertMatches(matches: List<Match>)
    suspend fun findMatchById(id: Int): Match?
    suspend fun updateMatch(match: Match)
}