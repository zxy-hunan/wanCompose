package com.zxy_hunan.compose.http

import android.annotation.SuppressLint
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.zxy_hunan.HttpService
import com.zxy_hunan.compose.http.interceptor.LogInterCeptor
import com.zxy_hunan.compose.http.interceptor.SetCookieInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object Net {
    private const val DEFAULT_TIMEOUT = 30000
    private lateinit var SERVICE: HttpService


    val retrfoit:HttpService
    get() {
        if (!Net::SERVICE.isInitialized){
            SERVICE=Retrofit.Builder()
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("https://www.wanandroid.com")
                .build()
                .create(HttpService::class.java)
        }
        return SERVICE
    }


    val  okHttp:OkHttpClient
    get() {
        return OkHttpClient.Builder().run {
            connectTimeout(DEFAULT_TIMEOUT.toLong(),TimeUnit.MILLISECONDS)
            readTimeout(DEFAULT_TIMEOUT.toLong(),TimeUnit.MILLISECONDS)
        writeTimeout(DEFAULT_TIMEOUT.toLong(),TimeUnit.MILLISECONDS)
            addInterceptor(SetCookieInterceptor())
            addInterceptor(LogInterCeptor())
            sslSocketFactory(createSSLSocketFactory())
            hostnameVerifier(TrustAllNameVerifier())
            build()
        }
    }

}

private fun createSSLSocketFactory(): SSLSocketFactory {
    lateinit var ssfFactory: SSLSocketFactory
    try {
        val sslFactory = SSLContext.getInstance("TLS")
        sslFactory.init(null,  arrayOf(TrustAllCerts()), SecureRandom());
        ssfFactory = sslFactory.socketFactory
    } catch (e: Exception) {
        print("SSL错误：${e.message}")
    }
    return ssfFactory
}



class TrustAllNameVerifier: HostnameVerifier {
    @SuppressLint("BadHostnameVerifier")
    override fun verify(hostname: String?, session: SSLSession?): Boolean = true
}

@SuppressLint("CustomX509TrustManager")
class TrustAllCerts : X509TrustManager {

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}