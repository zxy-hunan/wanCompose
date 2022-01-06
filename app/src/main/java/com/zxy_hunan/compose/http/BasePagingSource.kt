package com.zxy_hunan.compose.http

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zxy_hunan.compose.entity.BasicBean
import com.zxy_hunan.compose.entity.ListWrapper

class BasePagingSource<T : Any> constructor(
    private val callDataFromRemoteServer: suspend (page: Int) ->
    HttpResult<BasicBean<ListWrapper<T>>>
) : PagingSource<Int, T>() {
    private var page = -1
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        page = params.key ?: 0
        return when (val response = callDataFromRemoteServer(page)) {
            is HttpResult.Success -> {
                val data = response.result.data
                val hasNotNext = (data!!.datas.size < params.loadSize) && (data.over)
                LoadResult.Page(
                    data = response.result.data!!.datas,
                    prevKey = if (page - 1 > 0) page - 1 else null,
                    nextKey = if (hasNotNext) null else page + 1
                )
            }

            is HttpResult.Fail -> {
                LoadResult.Error(response.exception)
            }

        }

    }

}