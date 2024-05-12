package uz.otamurod.kmp.movieapp.android.presentation.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uz.otamurod.kmp.movieapp.android.R
import uz.otamurod.kmp.movieapp.android.presentation.theme.Red
import uz.otamurod.kmp.movieapp.domain.model.Movie

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    uiState: MovieDetailScreenState
) {
    Box(contentAlignment = Alignment.Center) {
        uiState.movie?.let { movie ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = "Movie Thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(320.dp)
                )

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        modifier = modifier.fillMaxWidth(),
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_button),
                            contentDescription = "Play button",
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Start watching now",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Released in ${movie.releaseDate.uppercase()}",
                        style = MaterialTheme.typography.labelLarge,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = movie.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        if (uiState.isLoading) {
            Row(
                modifier = modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Red)
            }
        }
    }
}

@Preview(name = "MovieDetailScreen", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewMovieDetailScreen() {
    MovieDetailScreen(
        uiState = MovieDetailScreenState(
            isLoading = true,
            movie = Movie(
                id = 1_000,
                title = "Movie Title Here",
                description = "Movie Description",
                imageUrl = "https://www.example.com/blabla",
                releaseDate = "11-05-2024"
            ),
            errorMessage = null
        )
    )
}