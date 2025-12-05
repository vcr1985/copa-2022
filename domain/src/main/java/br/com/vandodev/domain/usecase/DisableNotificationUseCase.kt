package br.com.vandodev.domain.usecase

import br.com.vandodev.domain.repository.NotificationRepository

class DisableNotificationUseCase(private val repository: NotificationRepository) {
    suspend operator fun invoke(id: Int) = repository.disableNotificationFor(id)
}