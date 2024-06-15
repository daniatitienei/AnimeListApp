@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.ad_coding.animelistapp.ui.screen.trending_anime

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ad_coding.animelistapp.ui.screen.trending_anime.composable.AnimeCard

@Composable
fun SharedTransitionScope.TrendingAnimeScreen(
    onAnimeClick: (String, String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: TrendingAnimeViewModel = hiltViewModel()
) {
    val animeData by viewModel.animeData.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        AnimatedContent(
            targetState = animeData.isEmpty(),
            label = "..."
        ) { isEmpty ->
            if (isEmpty) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = innerPadding.calculateTopPadding() + 10.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = innerPadding.calculateBottomPadding() + 10.dp,
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = "Trending Anime",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(animeData) { anime ->
                        AnimeCard(
                            anime = anime,
                            onClick = {
                                onAnimeClick(anime.attributes.posterImage.original, anime.id)
                            },
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    }
                }

            }
        }
    }
}

