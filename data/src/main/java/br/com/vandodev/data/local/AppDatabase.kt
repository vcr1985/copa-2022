package br.com.vandodev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.vandodev.data.local.model.MatchEntity

@Database(entities = [MatchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}