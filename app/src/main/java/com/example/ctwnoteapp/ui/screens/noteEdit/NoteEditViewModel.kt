package com.example.ctwnoteapp.ui.screens.noteEdit


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

data class NoteEditUiState(
	val notesList: List<Note> = listOf()
)

class NoteEditViewModel(private val notesRepository: NotesRepository) : ViewModel() {

	val homeUiState: StateFlow<NoteEditUiState> =
		notesRepository.getAllNotes()
			.map { notes ->
				NoteEditUiState(notes)
			}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = NoteEditUiState()
			)

	suspend fun updateNote(note: Note) {
		notesRepository.updateNote(note)
	}

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}

