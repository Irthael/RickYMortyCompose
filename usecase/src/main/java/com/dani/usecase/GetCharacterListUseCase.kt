package com.dani.usecase

import com.dani.data.repository.CharacterRepository
import com.dani.domain.Mycharacter
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(private val charactersRepository: CharacterRepository) {
    suspend fun getNormalList(offset: Int): Result<List<Mycharacter>> = charactersRepository.getCharactersList(offset)
    suspend fun getListFilter(offset: Int, name: String): List<Mycharacter> = charactersRepository.getCharactersListFilter(offset, name)
}

