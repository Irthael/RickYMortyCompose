package com.dani.composerickandmorty.model.mycharacters

import com.dani.composerickandmorty.model.APIService
import com.dani.data.source.RemoteDataSource
import com.dani.domain.Mycharacter
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(private val service: APIService): RemoteDataSource {

    override suspend fun getCharacterList(page: Int): Result<List<Mycharacter>> {
        return try {
            val itemsResponse = service.getAllCharacters(actualPage = page)
            if (itemsResponse.isSuccessful) {
                Result.success(itemsResponse.body()?.results?.toList() ?: emptyList())
            } else {
                Result.failure(Throwable("Error en la respuesta"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Error en la respuesta"))
        }
    }

    override suspend fun getCharacterFilterList(page: Int, name: String): Result<List<Mycharacter>> {
        return try {
            val itemsResponse = service.getAllCharactersFilter(actualPage = page, characterName = name)
            return if (itemsResponse.isSuccessful){
                Result.success(itemsResponse.body()?.results!!.toList())
            } else {
                Result.failure(Throwable("Error en la respuesta"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Error en la respuesta"))
        }
    }

    override suspend fun getCharacterInfo(characterID: Int): Result<Mycharacter> {
        return try {
            val itemsResponse = service.getCharactersInfo( characterId = characterID)
            return if (itemsResponse.isSuccessful){
                Result.success(itemsResponse.body()!!)
            } else {
                Result.failure(Throwable("Error en la respuesta"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Error en la respuesta"))
        }
    }
}
