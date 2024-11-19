package com.example.ctwnoteapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ctwnoteapp.R

@Composable
fun NotesFAB(onClick: () -> Unit, msg: String, icon: ImageVector) {
	FloatingActionButton(
		onClick = onClick,
		containerColor = MaterialTheme.colorScheme.primary
	) {
		Row(
			Modifier.padding(16.dp),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				imageVector = icon,
				contentDescription = stringResource(R.string.add_note),
			)
			Spacer(Modifier.width(10.dp))
			Text(msg, textAlign = TextAlign.Center)
		}
	}
}