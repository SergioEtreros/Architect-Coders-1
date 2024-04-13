package com.senkou.architectcoderssem.ui.screens.home

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.senkou.architectcoderssem.R
import com.senkou.architectcoderssem.ui.Movie
import com.senkou.architectcoderssem.ui.common.PermissionRequestEffect
import com.senkou.architectcoderssem.ui.common.getRegion
import com.senkou.architectcoderssem.ui.movies
import com.senkou.architectcoderssem.ui.theme.ArchitectCodersSemTheme
import kotlinx.coroutines.launch

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

  val title = stringResource(id = R.string.title)
  var appBarTitle by remember { mutableStateOf(title) }
  val coroutineScope = rememberCoroutineScope()

  val ctx = LocalContext.current

  PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
    if (granted) {
      coroutineScope.launch {
        val region = ctx.getRegion()
        appBarTitle = "$title ($region)"
      }
    } else {
      appBarTitle = "$title (${ctx.getString(R.string.permission_denied)})"
    }

  }

  Screen {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text(text = appBarTitle) },
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

