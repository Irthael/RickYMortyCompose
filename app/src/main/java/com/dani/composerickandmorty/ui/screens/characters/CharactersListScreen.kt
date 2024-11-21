package com.dani.composerickandmorty.ui.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dani.domain.Mycharacter

@Composable
fun CharactersListScreen(
    onClick: (Mycharacter) -> Unit,
    viewModel: CharactersViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()

    CharactersScreen2(
        loading = state.loading,
        characters = state.characters,
        onClick = onClick
    )
}

@Composable
fun CharactersScreen2(loading: Boolean = false,
                      characters: Result<List<Mycharacter>>,
                      onClick: (Mycharacter) -> Unit){

    if (loading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
    }

    characters.fold({ characters ->
        LazyVerticalGrid(columns = GridCells.Adaptive(180.dp)) {
            items(characters){
                CharacterItem(
                    character = it,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { onClick(it) }
                )
            }
        }
    }){
    }
}

@Composable
fun CharacterItem(character: Mycharacter, modifier: Modifier = Modifier){
    Column (
        modifier = modifier
    ){
        Card{
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp, 16.dp)
                    .weight(1f)
            )
        }
    }
}
