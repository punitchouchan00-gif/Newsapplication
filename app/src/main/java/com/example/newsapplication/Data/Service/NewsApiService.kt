package com.example.newsapplication.Data.Service

import android.util.Log
import com.example.newsapplication.Constant
import com.example.newsapplication.Data.Dto.NewsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import jakarta.inject.Inject

class NewsApiService @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getNews(): NewsDto {
        return client.get("latest?") {
            parameter("apikey", Constant.apiKey)
            parameter("q", "News")
            parameter("language", "en")
            parameter("country", "in")
            Log.d("API_CALL", "SUCCESS")

        }.body()
    }
}
