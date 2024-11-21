package com.dani.composerickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.dani.composerickandmorty.ui.navigation.Navigation
import com.dani.composerickandmorty.ui.theme.ComposeRickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyApp {
                Navigation()
            }
        }
    }
}

@Composable
fun RickAndMortyApp(content: @Composable () -> Unit){
    ComposeRickAndMortyTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
