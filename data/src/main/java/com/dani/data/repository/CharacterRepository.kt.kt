package com.dani.data.repository

import com.dani.data.source.LocalDataSource
import com.dani.data.source.RemoteDataSource
import com.dani.domain.Mycharacter
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val localDataSourceCharacters: LocalDataSource,
    private val remoteDataSourceCharacters: RemoteDataSource
){

    suspend fun getCharactersList(page: Int): Result<List<Mycharacter>>  {

        val result = remoteDataSourceCharacters.getCharacterList(page)
        if (result.isSuccess){
            localDataSourceCharacters.saveCharacters(result.getOrNull()!!)
        }
        val newItems = localDataSourceCharacters.getAllCharacters()
        return newItems
    }

    suspend fun getCharactersListFilter(offset: Int, name: String): List<Mycharacter> {
        val result = remoteDataSourceCharacters.getCharacterFilterList(offset, name)
        if (result.isSuccess) {
            localDataSourceCharacters.saveCharacters(result.getOrNull()!!)
        }
        return localDataSourceCharacters.getCharactersByName(name).map { it }
    }

    suspend fun findCharacterById(characterID: Int): Result<Mycharacter> =
        localDataSourceCharacters.getCharacterById(characterID)

    suspend fun findCharacterByIdInServer(characterId: Int): Result<Mycharacter?> {
        val result = remoteDataSourceCharacters.getCharacterInfo(characterId)
        return if (result.isSuccess) {
            result
        }else {
            localDataSourceCharacters.getCharacterById(characterId)
        }
    }
}
