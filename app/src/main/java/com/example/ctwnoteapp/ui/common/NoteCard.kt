package com.example.ctwnoteapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.R
import com.example.ctwnoteapp.data.Note

@Preview
@Composable
fun NoteCard(
	modifier: Modifier = Modifier,
	note: Note = Note(
		id = 0,
		title = "First note",
		text = "First note content",
		isFavorite = false,
		isTrashed = false
	),
	onFavoriteToggle: (Note) -> Unit = {},
	onTrashClick: (Note) -> Unit = {},
	restoreNote: (Note) -> Unit = {}
) {
	val textColor = MaterialTheme.colorScheme.onPrimaryContainer
	var isFavorite by remember { mutableStateOf(note.isFavorite) }

	Box(
		modifier = Modifier
			.padding(top = 16.dp, bottom = 16.dp)
	) {
		ElevatedCard(
			modifier.fillMaxWidth(),
			colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
		) {
			Column(
				modifier = Modifier.padding(16.dp)
			) {
				Row(
					horizontalArrangement = Arrangement.SpaceBetween,
					modifier = Modifier.fillMaxWidth()
				) {
					Text(
						text = note.title,
						color = textColor,
						textAlign = TextAlign.Center
					)
					//Icon Row
					Row(horizontalArrangement = Arrangement.SpaceEvenly) {
						IconButton(
							onClick = {
								isFavorite = !isFavorite
								onFavoriteToggle(note)
							}
						) {
							Icon(
								imageVector = if (!note.isFavorite) Icons.Outlined.FavoriteBorder else Icons.Filled.Favorite,
								contentDescription = stringResource(R.string.favorites),
								tint = MaterialTheme.colorScheme.onPrimaryContainer
							)
						}
						IconButton(
							onClick = { onTrashClick(note) }
						) {
							Icon(
								imageVector = Icons.Outlined.Delete,
								contentDescription = stringResource(R.string.trash),
								tint = MaterialTheme.colorScheme.onPrimaryContainer
							)
						}
						if (note.isTrashed) {
							IconButton(
								onClick = { restoreNote(note) }
							) {
								Icon(
									imageVector = Icons.Outlined.Refresh,
									contentDescription = stringResource(R.string.restore_note),
									tint = MaterialTheme.colorScheme.onPrimaryContainer
								)
							}
						}
					}
				}
				Spacer(Modifier.height(16.dp))
				Text(
					text = note.text,
					color = textColor,
					maxLines = 5,
					modifier = Modifier.fillMaxWidth()
				)
			}
		}
	}
}