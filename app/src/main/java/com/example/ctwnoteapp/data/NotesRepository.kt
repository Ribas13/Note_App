package com.example.ctwnoteapp.data

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

	fun getAllNotes(): Flow<List<Note>>

	fun getNote(id: Int): Flow<Note?>

	suspend fun insertNote(note: Note)

	suspend fun deleteNote(note: Note)

	suspend fun updateNote(note: Note)

	fun getAllTrash(): Flow<List<Note>>

	fun getAllFavorites(): Flow<List<Note>>

	suspend fun emptyTrash()
}