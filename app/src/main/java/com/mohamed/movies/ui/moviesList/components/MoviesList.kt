package com.mohamed.movies.ui.moviesList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.ui.model.ProgressTypes
import com.mohamed.movies.utils.OnBottomReached

@Composable
fun MoviesList(
    progressTypes: ProgressTypes? = ProgressTypes.NONE,
    moviesList: ArrayList<MovieListItem>? = arrayListOf(),
    onClickMovie: (MovieListItem) -> Unit = {},
    onBottomReached: () -> Unit = {},
) {

    val listState = rememberLazyListState()
    listState.OnBottomReached(progressTypes) {
        onBottomReached()
    }
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp), state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(moviesList ?: arrayListOf()) {
                MovieItem(
                    movie = it,
                    onClick = onClickMovie,
                )
            }
            if (progressTypes == ProgressTypes.PAGING_PROGRESS) {
                item {
                    PagingLoader()
                }
            }
        }
    }
}

@Composable
fun PagingLoader() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
