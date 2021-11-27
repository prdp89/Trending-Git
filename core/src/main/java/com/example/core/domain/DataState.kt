package com.example.core.domain

sealed class DataState <T> (val data: T? = null, val message: String? = null) {

    class Success<T> (data: T) : DataState<T>(data)
    class Failure<T> (message: String, data: T? = null) : DataState<T>(data, message)
    class Loading<T> (data: T? = null) : DataState<T>(data)
}