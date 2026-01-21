package com.example.newsapplication.Data.repositoryimpl

import com.example.newsapplication.Data.Dto.Results
import com.example.newsapplication.Data.Service.NewsApiService
import com.example.newsapplication.domain.Repostaory
import jakarta.inject.Inject
import com.example.newsapplication.Util.Result

class NewsRepositoryImpl @Inject constructor(private val apiService: NewsApiService):
    Repostaory {
    override suspend fun getnews(content: String, country: String): Result<List<Results>> {
        return try {
            val response = apiService.getNews()
            Result.Success(response.results)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }
}

