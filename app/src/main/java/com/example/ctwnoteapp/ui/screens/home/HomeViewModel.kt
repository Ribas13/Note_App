package com.example.ctwnoteapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctwnoteapp.data.Note
import com.example.ctwnoteapp.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
	val notesList: List<Note> = listOf()
)

class HomeViewModel(private val notesRepository: NotesRepository) : ViewModel() {

	val homeUiState: StateFlow<HomeUiState> =
		notesRepository.getAllNotes()
			.map { notes ->
				HomeUiState(notes.filter { !it.isTrashed})
			}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = HomeUiState()
			)

	fun addNote(note: Note) {
		viewModelScope.launch {
			notesRepository.insertNote(note)
		}
	}

	suspend fun trashNote(note: Note) {
			notesRepository.updateNote(note)
	}

	suspend fun removeNote(note: Note) {
		viewModelScope.launch {
			notesRepository.deleteNote(note)
		}
	}

	suspend fun favoriteNote(note: Note) {
			notesRepository.updateNote(note)
	}

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}

