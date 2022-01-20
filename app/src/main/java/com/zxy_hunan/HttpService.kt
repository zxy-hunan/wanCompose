package com.zxy_hunan

import com.zxy_hunan.compose.entity.Article
import com.zxy_hunan.compose.entity.BasicBean
import com.zxy_hunan.compose.entity.ListWrapper
import com.zxy_hunan.compose.entity.ParentBean
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpService {
    //首页
    @GET("/article/list/{page}/json")
    suspend fun getIndexList(@Path("page") page: Int): BasicBean<ListWrapper<Article>>

    @GET("/article/top/json")
    suspend fun getTopArticles(): BasicBean<MutableList<Article>>

    //体系
    @GET("/tree/json")
    suspend fun getStructureList(): BasicBean<MutableList<ParentBean>>

}