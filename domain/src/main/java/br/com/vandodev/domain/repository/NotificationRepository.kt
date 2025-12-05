package br.com.vandodev.domain.repository

interface NotificationRepository {
    suspend fun enableNotificationFor(id: Int)
    suspend fun disableNotificationFor(id: Int)
}