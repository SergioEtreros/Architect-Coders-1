package com.senkou.architectcoderssem.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.architectcoderssem.ui.Movie

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
  Column(
    modifier = Modifier.clickable(onClick = onClick),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    AsyncImage(
      model = movie.poster,
      contentDescription = movie.title,
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(2 / 3F)
        .clip(MaterialTheme.shapes.small)
    )

    Text(
      text = movie.title,
      style = MaterialTheme.typography.bodySmall,
      maxLines = 1,
      modifier = Modifier.padding(8.dp)
    )
  }
}