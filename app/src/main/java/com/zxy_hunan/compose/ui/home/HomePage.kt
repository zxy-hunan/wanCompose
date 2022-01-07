package com.zxy_hunan.compose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zxy_hunan.compose.acy.route.RouteName
import com.zxy_hunan.compose.acy.widget.HomeSearchBar
import com.zxy_hunan.compose.acy.widget.TextTabBar
import com.zxy_hunan.compose.ui.home.recommend.RecommendPage
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun HomePage(
    navCtrl: NavHostController, scaffoldState: ScaffoldState,
    homeIndex: Int = 0, viewModel: HomeVM = hiltViewModel(),
    onPageSelected: (position: Int) -> Unit
) {
    val titles by remember { viewModel.titles }
    val scopeState = rememberCoroutineScope()

    Column() {

        HomeSearchBar(onUserIconClick = {
            navCtrl.navigate(RouteName.ME) {
                popUpTo(navCtrl.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }, onSearchClick = {
        }) {}
        val pagerState = rememberPagerState(
            pageCount = titles.size, initialPage = homeIndex,
            initialOffscreenLimit = titles.size
        )

        TextTabBar(index = pagerState.currentPage, tabTexts = titles,
        onTabSelected = {
            scopeState.launch {
                pagerState.scrollToPage(it)
            }
        })

        HorizontalPager(state = pagerState, dragEnabled = false) {
            page ->
            onPageSelected(pagerState.currentPage)
            when(page){
            0 -> RecommendPage(navCtrl = navCtrl, scaffoldState = scaffoldState){}
            }
        }

    }


}


