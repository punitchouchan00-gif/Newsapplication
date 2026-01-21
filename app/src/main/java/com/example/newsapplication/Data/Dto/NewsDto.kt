package com.example.newsapplication.Data.Dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    val nextPage: String? = null,
    val results: List<Results> = emptyList(),
    val status: String? = null,
    val totalResults: Int? = null
)
