package com.example.ctwnoteapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ctwnoteapp.R
import com.example.ctwnoteapp.data.Note
import com.example.ctwnoteapp.ui.AppViewModelProvider
import com.example.ctwnoteapp.ui.common.NoteCard
import com.example.ctwnoteapp.ui.common.NotesAppBar
import com.example.ctwnoteapp.ui.common.NotesBottomBar
import com.example.ctwnoteapp.ui.common.NotesFAB
import com.example.ctwnoteapp.ui.common.NotesSearchBar
import com.example.ctwnoteapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


object HomeDestination : NavigationDestination {
	override val route = "home"
	override val titleRes = R.string.home
	var homeIcon: ImageVector = Icons.Filled.Home

	@Composable
	fun homeIconColor() = MaterialTheme.colorScheme.primary
	var favoritesIcon: ImageVector = Icons.Outlined.FavoriteBorder

	@Composable
	fun favoritesIconColor() = MaterialTheme.colorScheme.inversePrimary
	var trashIcon: ImageVector = Icons.Outlined.Delete

	@Composable
	fun trashIconColor() = MaterialTheme.colorScheme.inversePrimary
}

@Preview
@Composable
fun NoteAppHomePage(
	homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
	navHostController: NavHostController = NavHostController(LocalContext.current)
) {

	val homeUiState by homeViewModel.homeUiState.collectAsState()

	var title by remember { mutableStateOf("") }
	var text by remember { mutableStateOf("") }
	var showDialog by remember { mutableStateOf(false) }

	var searchQuery by remember { mutableStateOf("") }
	val filteredNotes = homeUiState.notesList.filter {
		it.title.contains(searchQuery, ignoreCase = true) ||
				it.text.contains(searchQuery, ignoreCase = true)
	}


	Scaffold(
		topBar = {
			Column {
				NotesAppBar(
					title = stringResource(R.string.app_name),
					modifier = Modifier,
				)
				NotesSearchBar(
					query = searchQuery,
					onQueryChange = { searchQuery = it },
					placeholderText = stringResource(R.string.search_bar_placeholder),
					modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 0.dp)
				)
			}
		},
		bottomBar = { NotesBottomBar(
			navHostController,
			homeIcon = HomeDestination.homeIcon,
			homeIconColor = HomeDestination.homeIconColor(),
			favoritesIcon = HomeDestination.favoritesIcon,
			favoritesIconColor = HomeDestination.favoritesIconColor(),
			trashIcon = HomeDestination.trashIcon,
			trashIconColor = HomeDestination.trashIconColor()
		) },
		floatingActionButton = {
			NotesFAB(
				onClick = { showDialog = true },
				msg = stringResource(R.string.add_note),
				icon = Icons.Outlined.Add
			)
		},
		floatingActionButtonPosition = FabPosition.End,
		content = { paddingValues ->
			HomeNotesList(
				notes = filteredNotes,
				viewModel = homeViewModel,
				modifier = Modifier.padding(16.dp),
				paddingValues = paddingValues
			)
			if (showDialog) {
				AddNoteDialog(
					title = title,
					content = text,
					onTitleChanged = { title = it },
					onContentChanged = { text = it },
					onAddNote = {
						homeViewModel.addNote(
							Note(
								title = title,
								text = text,
								isTrashed = false,
								isFavorite = false
							)
						)
						title = ""
						text = ""
						showDialog = false
					},
					onDismiss = { showDialog = false }
				)
			}
		}
	)
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(
	title: String = "",
	content: String = "",
	onTitleChanged: (String) -> Unit = {},
	onContentChanged: (String) -> Unit = {},
	onAddNote: () -> Unit = {},
	onDismiss: () -> Unit = {}
) {
	BasicAlertDialog(
		onDismissRequest = onDismiss,
		modifier = Modifier
			.background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(8.dp))
			.padding(8.dp)
	) {
		Column(verticalArrangement = Arrangement.SpaceEvenly) {
			OutlinedTextField(
				value = title,
				onValueChange = onTitleChanged,
				label = { Text("Title") },
				modifier = Modifier.fillMaxWidth(),
				maxLines = 2
			)
			OutlinedTextField(
				value = content,
				onValueChange = onContentChanged,
				label = { Text("Content") },
				modifier = Modifier.fillMaxWidth(),
				maxLines = 5
			)
			Spacer(Modifier.height(16.dp))
			Row(
				horizontalArrangement = Arrangement.SpaceAround,
				modifier = Modifier.fillMaxWidth()
			) {
				OutlinedButton(
					onClick = onDismiss,
					enabled = true,
					colors = ButtonColors(
						containerColor = MaterialTheme.colorScheme.primary,
						contentColor = MaterialTheme.colorScheme.primaryContainer,
						disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
						disabledContentColor = MaterialTheme.colorScheme.secondary
					)
				) { Text("Cancel") }
				OutlinedButton(
					onClick = onAddNote,
					enabled = true,
					colors = ButtonColors(
						containerColor = MaterialTheme.colorScheme.primary,
						contentColor = MaterialTheme.colorScheme.primaryContainer,
						disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
						disabledContentColor = MaterialTheme.colorScheme.secondary
					)
				) { Text("Add") }
			}
		}
	}
}

@Composable
private fun HomeNotesList(
	notes: List<Note>,
	viewModel: HomeViewModel,
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


//@Preview
//@Composable
//fun LightModePreview() {
//	NoteAppHomePage()
//}
//
//@Preview
//@Composable
//fun DarkModePreview() {
//	NoteAppHomePage()
//}
