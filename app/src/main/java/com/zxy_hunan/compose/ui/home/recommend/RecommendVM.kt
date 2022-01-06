package com.zxy_hunan.compose.ui.home.recommend

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zxy_hunan.compose.http.HttpRepository
import com.zxy_hunan.compose.http.PagingArticle
import javax.inject.Inject

class RecommendVM @Inject constructor(
    private var repo:HttpRepository
):ViewModel(){
    var isRefreshing = mutableStateOf(false)
    var pagingData = MutableLiveData<PagingArticle>(null)

    private fun homeData()=repo.getIndexData().cachedIn(viewModelScope)

    private fun getHomesList() {
        pagingData.value = null
        pagingData.value = homeData()
        isRefreshing.value = pagingData.value == null
    }

    fun refresh() {
        isRefreshing.value = true
        getHomesList()
    }
}