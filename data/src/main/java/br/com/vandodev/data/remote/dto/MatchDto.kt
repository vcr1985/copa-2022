package br.com.vandodev.data.remote.dto

import com.google.gson.annotations.SerializedName

// DTOs que correspondem EXATAMENTE ao JSON da API.

data class MatchDto(
    @SerializedName("nome")
    val name: String?,

    @SerializedName("estádio")
    val stadium: StadiumDto?,

    @SerializedName("equipe1")
    val teamA: String?,

    @SerializedName("equipe2")
    val teamB: String?,

    @SerializedName("data")
    val date: String?
)

data class StadiumDto(
    @SerializedName("nome")
    val name: String?,

    @SerializedName("imagem")
    val image: String?
)
