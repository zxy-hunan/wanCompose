package com.zxy_hunan.compose.http

import androidx.paging.PagingData
import com.zxy_hunan.compose.entity.Article
import com.zxy_hunan.compose.entity.ParentBean
import kotlinx.coroutines.flow.Flow

typealias PagingArticle = Flow<PagingData<Article>>
typealias ARTICLE = Flow<HttpResult<MutableList<Article>>>
typealias PARENT = Flow<HttpResult<MutableList<ParentBean>>>

interface HttpRepository {
    suspend fun getTopArticles(): ARTICLE
    suspend fun getStructureList(): PARENT
    fun getIndexData(): PagingArticle
}