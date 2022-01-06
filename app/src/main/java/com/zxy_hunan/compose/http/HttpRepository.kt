package com.zxy_hunan.compose.http

import androidx.paging.PagingData
import com.zxy_hunan.compose.entity.Article
import kotlinx.coroutines.flow.Flow

typealias PagingArticle = Flow<PagingData<Article>>

interface HttpRepository {

    fun getIndexData(): PagingArticle
}