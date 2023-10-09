package com.nst.baseproject.common.util

sealed class Resource<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T? = null, val message: String? = null) : Resource<T>(data)
    class Error<T>(error: String, data: T? = null) : Resource<T>(data, error)
}