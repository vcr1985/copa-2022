package br.com.vandodev.data.repository

import br.com.vandodev.data.local.TeamLocalDataSource
import br.com.vandodev.domain.repository.NotificationRepository
import br.com.vandodev.domain.repository.NotificationScheduler

class NotificationRepositoryImpl(
    private val localDataSource: TeamLocalDataSource,
    private val scheduler: NotificationScheduler
) : NotificationRepository {

    override suspend fun enableNotificationFor(id: Int) {
        val match = localDataSource.findMatchById(id)
        match?.let {
            localDataSource.updateMatch(it.copy(notificationEnabled = true))
            scheduler.schedule(it)
        }
    }

    override suspend fun disableNotificationFor(id: Int) {
        val match = localDataSource.findMatchById(id)
        match?.let {
            localDataSource.updateMatch(it.copy(notificationEnabled = false))
            scheduler.cancel(it)
        }
    }
}