package com.dani.composerickandmorty.model.database

import androidx.room.*
import com.dani.composerickandmorty.model.database.entities.CharactersEntity

@Dao
interface ApiCharactersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(itemList: List<CharactersEntity>)

    @Query("SELECT * FROM CharactersEntity")
    fun getAllCharacters(): List<CharactersEntity>

    @Query("SELECT * FROM CharactersEntity WHERE name LIKE '%' || :name ||'%'")
    fun getAllCharactersByName(name: String): List<CharactersEntity>

    @Query("SELECT * FROM CharactersEntity WHERE id = :id")
    fun getCharactersById(id: Int): CharactersEntity

    @Update
    fun updateCharacters(item: CharactersEntity)

    @Delete
    fun deleteCharacter(item: CharactersEntity)
}