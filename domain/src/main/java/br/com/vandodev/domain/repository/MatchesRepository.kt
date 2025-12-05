package br.com.vandodev.domain.repository

import br.com.vandodev.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    fun getMatches(): Flow<List<Match>>
}