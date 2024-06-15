package com.ad_coding.animelistapp.domain.model

data class AnimeData(
    val id: String,
    val attributes: Attributes,
)

data class Attributes(
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?,
    val synopsis: String?,
    val titles: Titles,
    val canonicalTitle: String?,
    val abbreviatedTitles: List<String>,
    val averageRating: String?,
    val userCount: Int?,
    val favoritesCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val ageRating: String?,
    val ageRatingGuide: String?,
    val subtype: String?,
    val status: String?,
    val posterImage: PosterImage,
    val coverImage: CoverImage,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val showType: String?
)

data class Titles(
    val en: String?
)

data class PosterImage(
    val tiny: String,
    val small: String,
    val medium: String,
    val large: String,
    val original: String
)

data class CoverImage(
    val tiny: String,
    val small: String,
    val large: String,
    val original: String
)