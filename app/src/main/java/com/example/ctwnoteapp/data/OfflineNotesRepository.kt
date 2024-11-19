package com.example.ctwnoteapp.data

import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val notesDao: NoteDao) : NotesRepository {

	override fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes()

	override fun getNote(id: Int): Flow<Note?> = notesDao.getNote(id)

	override suspend fun insertNote(note: Note) = notesDao.insert(note)

	override suspend fun deleteNote(note: Note) = notesDao.delete(note)

	override suspend fun updateNote(note: Note) = notesDao.update(note)

	override fun getAllTrash(): Flow<List<Note>> = notesDao.getAllTrash()

	override fun getAllFavorites(): Flow<List<Note>> = notesDao.getAllFavorites()

	override suspend fun emptyTrash() = notesDao.emptyTrash()

}