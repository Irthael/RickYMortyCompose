package com.dani.composerickandmorty.model.mycharacters

import com.dani.composerickandmorty.model.database.ApiCharactersDatabase
import com.dani.composerickandmorty.model.database.entities.CharactersEntity
import com.dani.data.source.LocalDataSource
import com.dani.domain.Mycharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.dani.domain.Mycharacter as CharactersDomain

class RoomDataSource @Inject constructor(database: ApiCharactersDatabase) : LocalDataSource {

    private val dao = database.apiCharactersDao()

    override suspend fun saveCharacters(list: List<CharactersDomain>) = withContext(Dispatchers.IO) {
        dao.insertCharacter(list.map(CharactersDomain::toCharactersEntity))
    }

    override suspend fun getAllCharacters(): Result<List<Mycharacter>> = withContext(Dispatchers.IO){
        Result.success(dao.getAllCharacters().map(CharactersEntity::toCharactersDomain))
    }

    override suspend fun getCharactersByName(name: String): List<Mycharacter> = withContext(Dispatchers.IO){
       dao.getAllCharactersByName(name).map(CharactersEntity::toCharactersDomain)
    }

    override suspend fun getCharacterById(characterID: Int) = withContext(Dispatchers.IO) {
        Result.success(dao.getCharactersById(characterID).toCharactersDomain())
    }

}
