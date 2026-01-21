package com.example.newsapplication.Data.Dto
import kotlinx.serialization.Serializable

@Serializable
data class SentimentStats(
    val negative: Double? = null,
    val neutral: Double? = null,
    val positive: Double? = null
)
