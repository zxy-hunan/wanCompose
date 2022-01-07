package com.zxy_hunan.compose.ui.home.recommend

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.zxy_hunan.compose.acy.widget.MultiStateItemView
import com.zxy_hunan.compose.entity.Article

@ExperimentalComposeUiApi
@Composable
fun RecommendPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: RecommendVM = hiltViewModel(),
    onScrollChangeListener: (position: Int) -> Unit
) {
    viewModel.refresh()
    val recommendData=viewModel.pagingData.value?.collectAsLazyPagingItems()
    val isLoaded=recommendData?.loadState?.prepend?.endOfPaginationReached?:false
    val currentPosition by remember {viewModel.currentListIndex}
    val refreshing by remember {viewModel.isRefreshing}
    val listState= rememberLazyListState(initialFirstVisibleItemIndex = currentPosition)
    val swipeRefreshState= rememberSwipeRefreshState(refreshing)

    SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.refresh() }) {

        LazyColumn(modifier = Modifier.fillMaxSize(),
        state = listState){
            if (!isLoaded){
                items(5){
                    MultiStateItemView(data = Article(),isLoading = true)
                }
            }else{
                if (recommendData!=null && recommendData.itemCount>0){
                    itemsIndexed(recommendData){
                        index, value ->
                        MultiStateItemView(data = value!!,
                        onSelected = {

                        },
                        onCollectClick = {

                        },
                        onUserClick = {

                        })
                    }
                }
            }
        }
    }
}