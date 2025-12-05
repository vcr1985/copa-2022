package br.com.vandodev.data.repository

import android.content.Context
import br.com.vandodev.domain.model.Match
import br.com.vandodev.domain.model.MatchData
import br.com.vandodev.domain.model.MatchInfo
import br.com.vandodev.domain.model.Team
import br.com.vandodev.domain.repository.MatchesRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MatchesRepositoryImpl(
    private val context: Context
) : MatchesRepository {

    override fun getMatches(): Flow<List<Match>> = flow {
        val jsonString = readJsonFromAssets("matches.json")
        val matchData = Gson().fromJson(jsonString, MatchData::class.java)
        val matches = matchData.matches.map { it.toMatch() }
        emit(matches)
    }

    private fun readJsonFromAssets(fileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    private fun MatchInfo.toMatch(): Match {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val localDateTime = LocalDateTime.parse("${this.date} ${this.time}", formatter)

        return Match(
            id = this.match_id,
            name = "${this.home_team} vs ${this.away_team}",
            teamA = Team(name = this.home_team, flag = this.home_team),
            teamB = Team(name = this.away_team, flag = this.away_team),
            stadium = this.stadium,
            date = localDateTime
        )
    }
}