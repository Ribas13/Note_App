package com.example.ctwnoteapp.data

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.UUID

@Entity(tableName = "notes")
data class Note(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val text: String,
	var isFavorite: Boolean,
	var isTrashed: Boolean
)
