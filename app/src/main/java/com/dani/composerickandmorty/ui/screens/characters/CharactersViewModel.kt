package com.dani.composerickandmorty.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.data.repository.CharacterRepository
import com.dani.domain.Mycharacter
import com.dani.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository,
    private val getCharacterListUseCase: GetCharacterListUseCase,

    ) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(characters = getCharacterListUseCase.getNormalList(0))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: Result<List<Mycharacter>> = Result.success(emptyList<Mycharacter>())
    )
}