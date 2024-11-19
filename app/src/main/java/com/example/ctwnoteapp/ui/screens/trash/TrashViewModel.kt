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

data class TrashUiState(
	val notesList: List<Note> = listOf()
)

class TrashViewModel(private val notesRepository: NotesRepository) : ViewModel() {

	val trashUiState: StateFlow<TrashUiState> =
		notesRepository.getAllTrash()
			.map { TrashUiState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = TrashUiState()
			)

	suspend fun removeNote(note: Note) {
		notesRepository.deleteNote(note)
	}

	suspend fun restoreNote(note: Note) {
		notesRepository.updateNote(note)
	}

	suspend fun emptyTrash() {
		notesRepository.emptyTrash()
	}

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}

