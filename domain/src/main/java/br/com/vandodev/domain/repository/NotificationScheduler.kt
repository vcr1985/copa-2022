package br.com.vandodev.domain.repository

import br.com.vandodev.domain.model.Match

interface NotificationScheduler {
    fun schedule(match: Match)
    fun cancel(match: Match)
}