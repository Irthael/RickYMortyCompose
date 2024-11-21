package com.dani.composerickandmorty.ui.screens.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dani.domain.Mycharacter

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()

    CharacterDetailScreenChar(
        loading = state.loading,
        character = state.character
    )
}

@Composable
fun CharacterDetailScreenChar(loading: Boolean, character: Result<Mycharacter?>){

    if (loading) {
        CircularProgressIndicator()
    }

    character.fold({ item ->
        if (item != null) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item{
                    Header(item)
                }
                if (item.episode.isNotEmpty()){
                    item{
                        Text(
                            text = "Episodes",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp)
                        )
                    }

                    items(item.episode){
                        Text(
                            text = it,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }) {  }
}

@Composable
private fun Header(character: Mycharacter) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.status,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
