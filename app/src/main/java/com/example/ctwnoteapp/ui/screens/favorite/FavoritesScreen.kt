package com.example.ctwnoteapp.ui.screens.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ctwnoteapp.R
import com.example.ctwnoteapp.data.Note
import com.example.ctwnoteapp.ui.AppViewModelProvider
import com.example.ctwnoteapp.ui.common.NoteCard
import com.example.ctwnoteapp.ui.common.NotesAppBar
import com.example.ctwnoteapp.ui.common.NotesBottomBar
import com.example.ctwnoteapp.ui.navigation.NavigationDestination
import com.example.ctwnoteapp.ui.screens.trash.FavoritesViewModel
import kotlinx.coroutines.launch


object FavoritesDestination : NavigationDestination {
	override val route = "favorites"
	override val titleRes = R.string.favorites
	var homeIcon: ImageVector = Icons.Outlined.Home

	@Composable
	fun homeIconColor() = MaterialTheme.colorScheme.inversePrimary
	var favoritesIcon: ImageVector = Icons.Filled.Favorite

	@Composable
	fun favoritesIconColor() = MaterialTheme.colorScheme.primary
	var trashIcon: ImageVector = Icons.Outlined.Delete

	@Composable
	fun trashIconColor() = MaterialTheme.colorScheme.inversePrimary
}

@Composable
fun FavoritesScreen(
	favoritesViewModel: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory),
	navHostController: NavHostController
) {

	val favoritesUiState by favoritesViewModel.favoritesUiState.collectAsState()

	Scaffold(
		topBar = {
			NotesAppBar(
				title = stringResource(R.string.app_name),
				modifier = Modifier
			)
		},
		bottomBar = {
			NotesBottomBar(
				navController = navHostController,
				homeIcon = FavoritesDestination.homeIcon,
				homeIconColor = FavoritesDestination.homeIconColor(),
				favoritesIcon = FavoritesDestination.favoritesIcon,
				favoritesIconColor = FavoritesDestination.favoritesIconColor(),
				trashIcon = FavoritesDestination.trashIcon,
				trashIconColor = FavoritesDestination.trashIconColor()
			)
		},
		floatingActionButton = {},
		floatingActionButtonPosition = FabPosition.End,
		content = { paddingValues ->
			FavoritesNotesList(
				notes = favoritesUiState.notesList,
				viewModel = favoritesViewModel,
				modifier = Modifier.padding(16.dp),
				paddingValues = paddingValues
			)
		}
	)
}

@Composable
private fun FavoritesNotesList(
	notes: List<Note>,
	viewModel: FavoritesViewModel,
	modifier: Modifier = Modifier,
	paddingValues: PaddingValues
) {
	val coroutineScope = rememberCoroutineScope()

	if (notes.isEmpty()) {
		Text(
			text = stringResource(R.string.no_notes_msg),
			textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxSize(),
			color = Color.Gray
		)
	} else {
		LazyColumn(
			contentPadding = paddingValues,
			modifier = modifier
		) {
			items(notes) { note ->
				NoteCard(
					note = note,
					onFavoriteToggle = {
						coroutineScope.launch {
							viewModel.favoriteNote(
								note.copy(isFavorite = !note.isFavorite)
							)
						}
					},
					onTrashClick = {
						coroutineScope.launch {
							viewModel.trashNote(
								note.copy(isTrashed = true)
							)
						}
					}
				)
			}
		}
	}
}