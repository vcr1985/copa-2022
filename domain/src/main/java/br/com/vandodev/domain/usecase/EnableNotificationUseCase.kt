package br.com.vandodev.domain.usecase

import br.com.vandodev.domain.repository.NotificationRepository

class EnableNotificationUseCase(private val repository: NotificationRepository) {
    suspend operator fun invoke(id: Int) = repository.enableNotificationFor(id)
}