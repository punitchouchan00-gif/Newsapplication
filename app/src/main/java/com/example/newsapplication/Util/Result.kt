package com.example.newsapplication.Util

sealed class Result<out T> {

    /**
     * A generic class that holds a value or an error status.
     * Used for representing different states of data loading operations.
     */
    object Initial : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Success<T>(val Data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

