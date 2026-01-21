package com.example.newsapplication.domain

import com.example.newsapplication.Data.Dto.NewsDto
import com.example.newsapplication.Data.Dto.Results
import com.example.newsapplication.Util.Result


interface Repostaory {

    suspend fun getnews(content: String, country: String) : Result<List<Results>>

}