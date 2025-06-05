package com.mohamed.movies.ui.movieDetails

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.mohamed.movies.domain.model.movieDetails.MovieDetailsReposeModel
import com.mohamed.movies.utils.alternate

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailContent(
    movie: MovieDetailsReposeModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.Black)
    ) {
        // Backdrop image
        movie.backdrop_path?.let {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w780$it"),
                contentDescription = "Backdrop",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movie.title.alternate(),
                color = Color.White
            )

            movie.tagline?.takeIf { it.isNotBlank() }?.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Genres
            if (movie.genreModels?.isNotEmpty() == true) {
                FlowRow(
                    modifier = Modifier.padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    movie.genreModels.forEach {
                        Chip(it?.name.alternate())
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Overview
            Text(
                text = "Overview",
                color = Color.White
            )
            Text(
                text = movie.overview.alternate(),
                color = Color.LightGray,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            // Runtime and Release Date
            Text(
                text = "Released: ${movie.release_date} · ${movie.runtime ?: "--"} min",
                color = Color.Gray
            )

            // Vote
            Text(
                text = "⭐ ${movie.vote_average} (${movie.vote_count} votes)",
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            val context = LocalContext.current
            // Homepage link
            movie.homepage?.let {
                Text(
                    text = "Visit official site",
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                            ContextCompat.startActivity(context, intent, null)
                        }
                        .padding(vertical = 4.dp)
                )
            }

            // Production companies
            if (movie.production_companies?.isNotEmpty() == true) {
                Text(
                    text = "Production",
                    color = Color.White,
                    modifier = Modifier.padding(top = 12.dp)
                )
                LazyRow {
                    items(movie.production_companies.size) { index ->
                        val company = movie.production_companies[index]
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .width(100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            company?.logo_path?.let {
                                Image(
                                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w200$it"),
                                    contentDescription = company.name,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Fit
                                )
                            }
                            Text(
                                text = company?.name.alternate(),
                                color = Color.White,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }

            movie.belongs_to_collection?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Part of: ${it.name}",
                    color = Color.LightGray
                )
            }
        }
    }
}


@Composable
fun Chip(label: String) {
    Box(
        modifier = Modifier
            .background(Color.DarkGray, shape = RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            color = Color.White
        )
    }
}
