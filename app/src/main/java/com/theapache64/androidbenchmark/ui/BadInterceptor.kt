package com.theapache64.androidbenchmark.ui

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class BadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // add 10 different headers to the request
        val request = chain.request().newBuilder()
            .build()
        // fake bad interceptor tasks by blocking the thread
        runBlocking {
            delay(100)
        }
        return chain.proceed(request)
    }
}