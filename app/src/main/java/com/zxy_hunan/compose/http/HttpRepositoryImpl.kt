package com.zxy_hunan.compose.http

import com.zxy_hunan.HttpService
import com.zxy_hunan.compose.ui.BaseRepository

class HttpRepositoryImpl constructor(private val apiService:HttpService):BaseRepository(),HttpRepository {
    override fun getIndexData()=pager { page ->apiService.getIndexList(page) }
}