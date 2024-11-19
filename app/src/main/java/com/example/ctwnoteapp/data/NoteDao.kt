package com.example.ctwnoteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(note: Note)

	@Update
	suspend fun update(note: Note)

	@Delete
	suspend fun delete(note: Note)

	@Query("SELECT * from notes ORDER BY isFavorite DESC, title ASC")
	fun getAllNotes(): Flow<List<Note>>

	@Query("SELECT * from notes where id = :id")
	fun getNote(id: Int): Flow<Note>

	@Query("SELECT * from notes where isTrashed = 1")
	fun getAllTrash(): Flow<List<Note>>

	@Query("SELECT * from notes where isFavorite = 1 and isTrashed = 0")
	fun getAllFavorites(): Flow<List<Note>>

	@Query("DELETE from notes WHERE isTrashed")
	suspend fun emptyTrash()
}