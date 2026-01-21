package com.example.newsapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.Data.Dto.Results
import com.example.newsapplication.Util.Result
import com.example.newsapplication.domain.Repostaory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: Repostaory) : ViewModel(){

    var content by mutableStateOf("")
        private set

    fun contant(newContent: String) {
        content = newContent
    }

    var snackbarMessage by mutableStateOf<String?>(null)
        private set

    fun searchNews() {
        if (content.isBlank()) {
            snackbarMessage = "Please enter a city name"
            return
        }

        viewModelScope.launch {
            _newsState.value = Result.Loading
            val result = newsRepository.getnews(content, "in")
            _newsState.value = result
        }
    }

    private val _newsState =
        MutableStateFlow<Result<List<Results>>>(Result.Initial)

    val newsState = _newsState.asStateFlow()
    fun getNewsArticles(country:String){
        viewModelScope.launch {
            _newsState.value = Result.Loading
            try {
                val result = newsRepository.getnews(content, country)
                _newsState.value = result

            }catch (e: Exception){
                _newsState.value = Result.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
