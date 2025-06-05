package com.mohamed.movies.ui.moviesList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.utils.alternate

@Composable
fun SearchBarWithPopupSuggestions(
    query: String,
    onQueryChanged: (String) -> Unit,
    suggestions: List<MovieListItem>,
    showSuggestions: Boolean,
    onMovieSelected: (MovieListItem) -> Unit,
    onSearchSubmit: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var textFieldPosition by remember { mutableStateOf(Offset.Zero) }
    var textFieldHeight by remember { mutableIntStateOf(0) }

    val focusRequester = remember { FocusRequester() }
    var hasFocus by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChanged,
                label = { Text("Search movies...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        hasFocus = it.isFocused
                    }
                    .onGloballyPositioned { coordinates ->
                        val pos = coordinates.localToWindow(Offset.Zero)
                        textFieldPosition = pos
                        textFieldHeight = coordinates.size.height
                    },
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchSubmit()
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onDismiss()
                    }
                )
            )
        }

        if (showSuggestions && suggestions.isNotEmpty()) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(
                    x = textFieldPosition.x.toInt(),
                    y = (textFieldPosition.y + textFieldHeight).toInt()
                ),
                onDismissRequest = onDismiss
            ) {
                Card(
                    modifier = Modifier
                        .widthIn(min = 200.dp, max = 400.dp)
                        .heightIn(max = 250.dp)
                        .shadow(4.dp)
                        .background(Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    LazyColumn {
                        items(suggestions.size) { index ->
                            val movie = suggestions[index]
                            Text(
                                text = movie.title.alternate(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onMovieSelected(movie)
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                        onDismiss()
                                    }
                                    .padding(12.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(hasFocus) {
        if (!hasFocus) onDismiss()
    }
}
