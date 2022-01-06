package com.zxy_hunan.compose.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zxy_hunan.compose.entity.BasicBean
import com.zxy_hunan.compose.entity.ListWrapper
import com.zxy_hunan.compose.http.BasePagingSource
import com.zxy_hunan.compose.http.HttpResult
import com.zxy_hunan.compose.http.PagingFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

open class BaseRepository {
    fun <T> flowable(call: suspend () -> BasicBean<T>): Flow<HttpResult<T>> {
        return flow {
            val result = try {
                val response = call()
                if (response.errorCode == 0) {
                    if (response.data != null) {
                        HttpResult.Success(response.data!!)
                    } else {
                        throw Exception("the result is null")
                    }
                } else {
                    throw Exception(response.errorMsg)
                }
            } catch (e: Exception) {
                HttpResult.Fail(e)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun <T : Any> pager(
        initKey: Int = 0, baseConfig: PagingConfig = PagingFactory().pagingConfig,
        callAction: suspend (page: Int) -> BasicBean<ListWrapper<T>>
    ): Flow<PagingData<T>> {
        return Pager(
            config = baseConfig,
            initialKey = initKey,
            pagingSourceFactory = {
                BasePagingSource {
                    try {
                        HttpResult.Success(callAction(it))
                    } catch (e: Exception) {
                        HttpResult.Fail(e)
                    }
                }
            }
        ).flow
    }
}