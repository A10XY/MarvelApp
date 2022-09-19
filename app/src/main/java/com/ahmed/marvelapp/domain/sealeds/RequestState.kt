package com.ahmed.marvelapp.domain.sealeds

sealed class RequestState<out T> {
    data class Success<T>(val data: T?) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}