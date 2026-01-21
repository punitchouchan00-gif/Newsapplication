package com.example.newsapplication.Data.Dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement


@Serializable
data class Results(

    val ai_org: JsonElement? = null,
    val ai_region: JsonElement? = null,
    val ai_summary: String? = null,
    val ai_tag: JsonElement? = null,

    val article_id: String? = null,
    val category: JsonElement? = null,
    val content: String? = null,
    val country: JsonElement? = null,
    val creator: JsonElement? = null,

    val datatype: String? = null,
    val description: String? = null,
    val duplicate: Boolean? = null,
    val fetched_at: String? = null,
    val image_url: String? = null,

    val keywords: JsonElement? = null,
    val language: String? = null,
    val link: String? = null,
    val pubDate: String? = null,
    val pubDateTZ: String? = null,
    val sentiment: String? = null,

    // already fixed
    val sentiment_stats: JsonElement? = null,

    val source_icon: String? = null,
    val source_id: String? = null,
    val source_name: String? = null,
    val source_priority: Int? = null,
    val source_url: String? = null,
    val title: String? = null,
    val video_url: String? = null
)
