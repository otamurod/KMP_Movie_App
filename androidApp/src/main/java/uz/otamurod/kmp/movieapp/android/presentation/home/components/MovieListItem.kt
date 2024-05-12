package uz.otamurod.kmp.movieapp.android.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uz.otamurod.kmp.movieapp.android.R
import uz.otamurod.kmp.movieapp.domain.model.Movie

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {
    Card(
        modifier = modifier
            .height(220.dp)
            .clickable { onMovieClick(movie) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))) {
            Box(
                modifier = modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    placeholder = painterResource(id = R.drawable.movie_placeholder),
                    model = movie.imageUrl,
                    contentDescription = "Movie Thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
                )

                Surface(
                    color = Color.Black.copy(alpha = 0.6f),
                    modifier = modifier
                        .size(50.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.play_button),
                        contentDescription = "Play button",
                        modifier = modifier
                            .align(Alignment.Center)
                            .padding(12.dp)
                    )
                }
            }

            Column(
                modifier = modifier.padding(10.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview(name = "MovieListItem", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewMovieListItem() {
    MovieListItem(
        modifier = Modifier,
        movie = Movie(
            id = 1_000,
            title = "Movie Title Here",
            description = "Movie Description",
            imageUrl = "https://www.example.com/blabla",
            releaseDate = "11-05-2024"
        ),
        {}
    )
}