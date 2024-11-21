package com.dani.composerickandmorty.ui.screens.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.composerickandmorty.ui.navigation.NavArg
import com.dani.data.repository.CharacterRepository
import com.dani.domain.Mycharacter
import com.dani.usecase.GetCharacterInfoServerUseCase
import com.dani.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCharacterListUseCase : GetCharacterInfoServerUseCase
) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(character = getCharacterListUseCase.invoke(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val character: Result<Mycharacter?> = Result.success(null)
    )
}