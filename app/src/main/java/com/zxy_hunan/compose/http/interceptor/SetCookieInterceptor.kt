package com.zxy_hunan.compose.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class SetCookieInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
        val builder=request.newBuilder()
        val domain=request.url().host()
        return chain.proceed(builder.build())
    }
}