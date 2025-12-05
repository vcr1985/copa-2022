package br.com.vandodev.domain.usecase

import br.com.vandodev.domain.repository.MatchesRepository

class GetMatchesUseCase(private val repository: MatchesRepository) {
    operator fun invoke() = repository.getMatches()
}