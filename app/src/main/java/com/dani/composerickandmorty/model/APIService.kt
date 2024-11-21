package com.dani.composerickandmorty.model

import com.dani.domain.CharacterListResponseData
import com.dani.domain.Mycharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("/api/character")
    suspend fun getAllCharacters(
        @Query("page") actualPage: Int
    ): Response<CharacterListResponseData>

    @GET("/api/character")
    suspend fun getAllCharactersFilter(
        @Query("page") actualPage: Int,
        @Query("name") characterName: String
    ): Response<CharacterListResponseData>

    @GET("/api/character/{characterId}")
    suspend fun getCharactersInfo(
        @Path("characterId") characterId: Int
    ): Response<Mycharacter>
}
