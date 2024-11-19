package com.example.ctwnoteapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ctwnoteapp.R
import com.example.ctwnoteapp.ui.screens.favorite.FavoritesDestination
import com.example.ctwnoteapp.ui.screens.home.HomeDestination.route
import com.example.ctwnoteapp.ui.screens.trash.TrashDestination

@Composable
fun NotesBottomBar(
	navController: NavHostController,
	homeIcon: ImageVector,
	homeIconColor: Color,
	favoritesIcon: ImageVector,
	favoritesIconColor: Color,
	trashIcon: ImageVector,
	trashIconColor: Color,
) {
	BottomAppBar(
		containerColor = MaterialTheme.colorScheme.surface,
		tonalElevation = 4.dp,
		contentPadding = PaddingValues(20.dp)
	) {
		Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
			IconButton(
				onClick = {
					navController.navigate(route) {
						popUpTo(navController.graph.startDestinationId) { inclusive = true }
						launchSingleTop = true
					}
				}) {
				Icon(
					homeIcon,
					contentDescription = stringResource(R.string.home),
					tint = homeIconColor
				)
			}
			IconButton(onClick = {
				navController.navigate(FavoritesDestination.route) {
					popUpTo(navController.graph.startDestinationId) { inclusive = true }
					launchSingleTop = true
				}
			}) {
				Icon(
					favoritesIcon,
					contentDescription = stringResource(R.string.favorites),
					tint = favoritesIconColor
				)
			}
			IconButton(onClick = {
				navController.navigate(TrashDestination.route) {
					popUpTo(navController.graph.startDestinationId) { inclusive = true }
					launchSingleTop = true
				}
			}) {
				Icon(
					trashIcon,
					contentDescription = stringResource(R.string.trash),
					tint = trashIconColor
				)
			}
		}
	}
}