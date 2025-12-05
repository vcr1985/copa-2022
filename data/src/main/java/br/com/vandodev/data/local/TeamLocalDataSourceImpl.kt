package br.com.vandodev.data.local

import br.com.vandodev.data.local.mappers.toDomain
import br.com.vandodev.data.local.mappers.toEntity
import br.com.vandodev.domain.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamLocalDataSourceImpl(private val matchDao: MatchDao) : TeamLocalDataSource {

    override fun getMatches(): Flow<List<Match>> {
        return matchDao.getMatches().map { it.map { entity -> entity.toDomain() } }
    }

    override suspend fun upsertMatches(matches: List<Match>) {
        matchDao.upsertAll(matches.map { it.toEntity() })
    }

    override suspend fun findMatchById(id: Int): Match? {
        return matchDao.findById(id)?.toDomain()
    }

    override suspend fun updateMatch(match: Match) {
        matchDao.update(match.toEntity())
    }
}