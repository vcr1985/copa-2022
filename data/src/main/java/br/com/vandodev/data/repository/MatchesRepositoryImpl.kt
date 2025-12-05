package br.com.vandodev.data.repository

import br.com.vandodev.data.local.TeamLocalDataSource
import br.com.vandodev.data.remote.TeamRemoteDataSource
import br.com.vandodev.domain.model.Match
import br.com.vandodev.domain.repository.MatchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class MatchesRepositoryImpl(
    private val remoteDataSource: TeamRemoteDataSource,
    private val localDataSource: TeamLocalDataSource
) : MatchesRepository {

    override fun getMatches(): Flow<List<Match>> = channelFlow {
        // Inicia a observação do banco de dados local. Os dados existentes (se houver) são enviados primeiro.
        val localDataFlow = launch {
            localDataSource.getMatches().collect { matches ->
                send(matches)
            }
        }

        // Em paralelo, busca os dados da rede. Sem try-catch para que o erro se propague para o ViewModel.
        val remoteMatches = remoteDataSource.getMatches()
        localDataSource.upsertMatches(remoteMatches)
    }
}