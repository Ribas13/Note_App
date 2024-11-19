package com.example.ctwnoteapp.ui.screens.noteEdit

import androidx.compose.ui.res.stringResource
import com.example.ctwnoteapp.R
import com.example.ctwnoteapp.ui.navigation.NavigationDestination

object NoteEditScreen: NavigationDestination {
	override val route = "edit note"
	override val titleRes = R.string.edit_note_screen
}