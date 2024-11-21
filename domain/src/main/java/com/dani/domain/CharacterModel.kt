package com.dani.domain

data class CharacterListResponseData (
    val info: InfoResponse,
    val results: List<Mycharacter>
)

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String,
)

data class Mycharacter (
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: Origin = Origin("", ""),
    val location: Location = Location("", ""),
    val image: String = "",
    val episode: List<String> = emptyList(),
    val url: String = "",
    val created: String = "",
)

data class Origin (
    val name: String,
    val url: String
)
data class Location (
    val name: String,
    val url: String
)


