package com.example.ctwnoteapp.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppBar(
	title: String,
	modifier: Modifier = Modifier,
) {
	CenterAlignedTopAppBar(
		title = {
			Row(verticalAlignment = Alignment.CenterVertically) {
				Icon(
					imageVector = Icons.Rounded.Create,
					contentDescription = null,
				)
				Spacer(Modifier.width(10.dp))
				Text(
					text = title,
					textAlign = TextAlign.Center,
					color = MaterialTheme.colorScheme.primary,
					style = MaterialTheme.typography.headlineSmall
				)
			}
		},
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.background,
			titleContentColor = MaterialTheme.colorScheme.primary
		),
		modifier = modifier.fillMaxWidth()
	)
}