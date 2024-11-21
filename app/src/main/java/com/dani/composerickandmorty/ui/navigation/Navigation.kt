package com.dani.composerickandmorty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dani.composerickandmorty.ui.screens.characterdetail.CharacterDetailScreen
import com.dani.composerickandmorty.ui.screens.characters.CharactersListScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.CharactersListScreen.route
    ) {
        composable(NavItem.CharactersListScreen){
            CharactersListScreen(
                onClick = { character ->
                    navController.navigate(NavItem.CharacterDetailScreen.createRoute(character.id))
                }
            )
        }

        composable(NavItem.CharacterDetailScreen){
            CharacterDetailScreen()
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T{
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}
