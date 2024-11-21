package com.dani.usecase

import com.dani.data.repository.CharacterRepository
import com.dani.domain.Mycharacter
import javax.inject.Inject

class GetCharacterListFilterUseCase @Inject constructor(private val charactersRepository: CharacterRepository) {
    suspend fun invoke(offset: Int, name: String): List<Mycharacter> =
        charactersRepository.getCharactersListFilter(offset, name)
}
