package com.example.ctwnoteapp.ui.screens.trash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctwnoteapp.data.Note
import com.example.ctwnoteapp.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FavoritesUiState(
	val notesList: List<Note> = listOf()
)

class FavoritesViewModel(private val notesRepository: NotesRepository) : ViewModel() {

	val favoritesUiState: StateFlow<FavoritesUiState> =
		notesRepository.getAllFavorites()
			.map { FavoritesUiState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = FavoritesUiState()
			)

	suspend fun favoriteNote(note: Note) {
		notesRepository.updateNote(note)
	}

	suspend fun trashNote(note: Note) {
		notesRepository.updateNote(note)
	}




	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}

