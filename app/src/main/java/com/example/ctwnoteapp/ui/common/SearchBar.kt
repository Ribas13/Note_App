package com.example.ctwnoteapp.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ctwnoteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesSearchBar(
	query: String,
	onQueryChange: (String) -> Unit,
	placeholderText: String = stringResource(R.string.search_bar_placeholder),
	modifier: Modifier = Modifier
) {
	SearchBar(
		query = query,
		onQueryChange = onQueryChange,
		onSearch = {},
		active = false,
		onActiveChange = {},
		placeholder = { Text(placeholderText) },
		leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
		trailingIcon = {
			if (query.isNotEmpty()) {
				IconButton(onClick = { onQueryChange("") }) {
					Icon(Icons.Default.Close, contentDescription = null)
				}
			}
		},
		modifier = modifier.fillMaxWidth(),
	){}
}