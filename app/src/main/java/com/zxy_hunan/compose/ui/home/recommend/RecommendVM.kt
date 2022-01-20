package com.zxy_hunan.compose.ui.home.recommend

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zxy_hunan.compose.entity.Article
import com.zxy_hunan.compose.http.HttpRepository
import com.zxy_hunan.compose.http.HttpResult
import com.zxy_hunan.compose.http.PagingArticle
import com.zxy_hunan.compose.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class RecommendVM @Inject constructor(
    private var repo:HttpRepository
):BaseVM<Article>(){
    var isRefreshing = mutableStateOf(false)
    var pagingData = MutableLiveData<PagingArticle>(null)
    var topArticles = mutableStateListOf<Article>()

    private fun homeData()=repo.getIndexData().cachedIn(viewModelScope)

    private fun getHomesList() {
        pagingData.value = null
        pagingData.value = homeData()
        isRefreshing.value = pagingData.value == null
    }

    fun refresh() {
        isRefreshing.value = true
        loadTopArticles()
        getHomesList()
    }

    private fun loadTopArticles() {
        async {
            repo.getTopArticles().collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        topArticles.clear()
                        topArticles.addAll(response.result)
                    }
                    is HttpResult.Fail -> {
                        topArticles.clear()
                    }
                }
            }
        }
    }

    override fun start() {
        initThat {
            isRefreshing.value = true
            loadTopArticles()
            getHomesList()
        }

    }
}