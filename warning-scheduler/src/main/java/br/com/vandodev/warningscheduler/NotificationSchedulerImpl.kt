package br.com.vandodev.warningscheduler

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import br.com.vandodev.domain.model.Match
import br.com.vandodev.domain.repository.NotificationScheduler
import java.time.Duration
import java.time.LocalDateTime

class NotificationSchedulerImpl(private val context: Context) : NotificationScheduler {

    private val workManager = WorkManager.getInstance(context)

    override fun schedule(match: Match) {
        val now = LocalDateTime.now()
        val matchTime = match.date
        val delay = Duration.between(now, matchTime)

        if (delay.isNegative) return

        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(delay)
            .setInputData(workDataOf(
                NotificationWorker.KEY_MATCH_NAME to match.name,
                NotificationWorker.KEY_TEAM_A to match.teamA.name,
                NotificationWorker.KEY_TEAM_B to match.teamB.name
            ))
            .addTag(match.id.toString())
            .build()

        workManager.enqueue(workRequest)
    }

    override fun cancel(match: Match) {
        workManager.cancelAllWorkByTag(match.id.toString())
    }
}