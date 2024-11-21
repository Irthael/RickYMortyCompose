package com.dani.usecase

import com.dani.data.repository.CharacterRepository
import com.dani.domain.Mycharacter
import javax.inject.Inject

class GetCharacterInfoServerUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend fun invoke(characterID: Int): Result<Mycharacter?> = characterRepository.findCharacterByIdInServer(characterID)
}
