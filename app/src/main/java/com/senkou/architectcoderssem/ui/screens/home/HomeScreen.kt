package com.senkou.architectcoderssem.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.architectcoderssem.ui.Movie
import com.senkou.architectcoderssem.ui.movies
import com.senkou.architectcoderssem.ui.theme.ArchitectCodersSemTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
  ArchitectCodersSemTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background,
      content = content
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClick: (Movie) -> Unit) {
  Screen {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text(text = "Movies") },
          scrollBehavior = scrollBehavior
        )
      },
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
      contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->

      LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(horizontal = 4.dp),
        contentPadding = paddingValues
      ) {
        items(movies) { movie ->
          MovieItem(movie = movie, onClick = { onClick(movie) })
        }
      }
    }
  }
}

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