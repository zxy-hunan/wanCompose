package com.zxy_hunan.compose.http.moudle

import com.zxy_hunan.HttpService
import com.zxy_hunan.compose.http.HttpRepository
import com.zxy_hunan.compose.http.HttpRepositoryImpl
import com.zxy_hunan.compose.http.Net
import com.zxy_hunan.compose.http.interceptor.LogInterCeptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideApiService():HttpService=Net.retrfoit

    @Singleton
    @Provides
    fun provideOkhttp():OkHttpClient=Net.okHttp

    @Singleton
    @Provides
    fun provideLogInterceptor():Interceptor=LogInterCeptor()

    @Provides
    fun provideRepository(apiService: HttpService):HttpRepository{
        return HttpRepositoryImpl(apiService)
    }
}