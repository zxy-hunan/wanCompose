package com.zxy_hunan.compose.http

import androidx.paging.PagingConfig

class PagingFactory {
    val pagingConfig= PagingConfig(
        pageSize=20,
        enablePlaceholders = true,
        prefetchDistance = 4,
        initialLoadSize = 20

    )
}