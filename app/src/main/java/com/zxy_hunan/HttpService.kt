package com.zxy_hunan

import com.zxy_hunan.compose.entity.Article
import com.zxy_hunan.compose.entity.BasicBean
import com.zxy_hunan.compose.entity.ListWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpService {
    //首页
    @GET("/article/list/{page}/json")
    suspend fun getIndexList(@Path("page") page: Int): BasicBean<ListWrapper<Article>>
}