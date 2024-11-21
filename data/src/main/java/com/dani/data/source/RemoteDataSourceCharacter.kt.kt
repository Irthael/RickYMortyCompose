package com.dani.data.source

import com.dani.domain.Mycharacter

interface RemoteDataSource {
    suspend fun getCharacterList(page: Int): Result<List<Mycharacter>>
    suspend fun getCharacterFilterList(page: Int, name: String): Result<List<Mycharacter>>
    suspend fun getCharacterInfo(characterID: Int): Result<Mycharacter>
}