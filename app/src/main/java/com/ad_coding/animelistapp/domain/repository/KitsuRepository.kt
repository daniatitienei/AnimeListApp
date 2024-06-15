package com.ad_coding.animelistapp.domain.repository

import com.ad_coding.animelistapp.data.network.dto.AnimeDataDto
import com.ad_coding.animelistapp.domain.model.AnimeData
import com.skydoves.sandwich.ApiResponse

interface KitsuRepository {

    suspend fun getTrendingAnime(): List<AnimeData>

    suspend fun getAnime(id: Int): AnimeData?
}