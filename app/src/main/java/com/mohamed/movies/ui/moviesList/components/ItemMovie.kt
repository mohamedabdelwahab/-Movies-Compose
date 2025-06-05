package com.mohamed.movies.ui.moviesList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.utils.Constants.URL.IMAGE_BASE_URL
import com.mohamed.movies.utils.alternate

@Composable
fun MovieItem(movie: MovieListItem, onClick: (MovieListItem) -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { onClick(movie) }
    ) {
        AsyncImage(
            model = "${IMAGE_BASE_URL}/${movie.poster_path}",
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie.title.alternate(),
            color = Color.Black,
            style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        )
        Text(
            text = movie.release_date.alternate(),
            color = Color.Black,
            style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        )

    }

}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    val sampleMovie = MovieListItem(
        id = 1,
        title = "Inception",
        release_date = "2010-07-16",
        poster_path = "/tUae3mefrDVTgm5mRzqWnZK6fOP.jpg"
    )

    MovieItem(movie = sampleMovie, onClick = {})
}


