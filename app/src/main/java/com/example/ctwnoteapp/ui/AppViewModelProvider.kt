package com.example.ctwnoteapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ctwnoteapp.NotesApplication
import com.example.ctwnoteapp.ui.screens.home.HomeViewModel
import com.example.ctwnoteapp.data.AppContainer
import com.example.ctwnoteapp.ui.screens.trash.FavoritesViewModel
import com.example.ctwnoteapp.ui.screens.trash.TrashViewModel

object AppViewModelProvider {
	val Factory = viewModelFactory {


		initializer {
			HomeViewModel(noteApplication().container.notesRepository)
		}

		initializer {
			FavoritesViewModel(noteApplication().container.notesRepository)
		}

		initializer {
			TrashViewModel(noteApplication().container.notesRepository)
		}
	}
}

fun CreationExtras.noteApplication(): NotesApplication =
	(this[AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)