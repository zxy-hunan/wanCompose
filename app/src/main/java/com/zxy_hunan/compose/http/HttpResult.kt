package com.zxy_hunan.compose.http

import java.lang.Exception

sealed class HttpResult<out T> {
    data class Success<T>(val result:T):HttpResult<T>()
    data class Fail(val exception: Exception):HttpResult<Nothing>()
}