@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.ad_coding.animelistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.ad_coding.animelistapp.ui.screen.anime.AnimeScreen
import com.ad_coding.animelistapp.ui.screen.anime.AnimeViewModel
import com.ad_coding.animelistapp.ui.theme.AnimeListAppTheme
import com.ad_coding.animelistapp.ui.screen.trending_anime.TrendingAnimeScreen
import com.skydoves.sandwich.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeListAppTheme {
                val navController = rememberNavController()

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        android.graphics.Color.TRANSPARENT
                    )
                )

                SharedTransitionLayout {
                    NavHost(navController = navController, startDestination = TrendingAnimeRoute) {
                        composable<TrendingAnimeRoute> {
                            TrendingAnimeScreen(
                                animatedVisibilityScope = this,
                                onAnimeClick = { coverUrl, id ->
                                    navController.navigate(
                                        AnimeRoute(coverUrl = coverUrl, id = id)
                                    )
                                }
                            )
                        }

                        composable<AnimeRoute> {
                            val args = it.toRoute<AnimeRoute>()

                            AnimeScreen(
                                animatedVisibilityScope = this,
                                id = args.id.toInt(),
                                coverImage = args.coverUrl
                            )
                        }
                    }
                }

            }
        }
    }
}

@Serializable
data object TrendingAnimeRoute

@Serializable
data class AnimeRoute(val coverUrl: String, val id: String)
