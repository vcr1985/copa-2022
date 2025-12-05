package br.com.vandodev.data.remote

import br.com.vandodev.domain.model.Match

interface TeamRemoteDataSource {
    suspend fun getMatches(): List<Match>
}