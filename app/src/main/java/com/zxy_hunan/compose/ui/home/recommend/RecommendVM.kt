package com.zxy_hunan.compose.ui.home.recommend

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zxy_hunan.compose.entity.Article
import com.zxy_hunan.compose.http.HttpRepository
import com.zxy_hunan.compose.http.PagingArticle
import com.zxy_hunan.compose.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendVM @Inject constructor(
    private var repo:HttpRepository
):BaseVM<Article>(){
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