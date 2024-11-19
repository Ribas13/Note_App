package com.example.ctwnoteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.ctwnoteapp.ui.screens.home.NoteAppHomePage
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ctwnoteapp.ui.screens.favorite.FavoritesDestination
import com.example.ctwnoteapp.ui.screens.favorite.FavoritesScreen
import com.example.ctwnoteapp.ui.screens.home.HomeDestination
import com.example.ctwnoteapp.ui.screens.trash.TrashDestination
import com.example.ctwnoteapp.ui.screens.trash.TrashScreen


@Composable
fun NotesNavHost(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	NavHost(
		navController = navController,
		startDestination = HomeDestination.route,
		modifier = modifier,
	) {
		composable(route = HomeDestination.route) {
			NoteAppHomePage(navHostController = navController)
		}
		composable(route = FavoritesDestination.route) {
			FavoritesScreen(navHostController = navController)
		}
		composable(route = TrashDestination.route) {
			TrashScreen(navHostController = navController)
		}
	}
}
