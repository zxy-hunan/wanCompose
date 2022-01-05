package com.zxy_hunan.compose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomePage(
    navCtrl: NavHostController, scaffoldState: ScaffoldState,
    homeIndex: Int = 0, viewModel: HomeVM = hiltViewModel(),
    onPageSelected: (position: Int) -> Unit
) {
    val titles by remember { viewModel.titles }
    val scopeState = rememberCoroutineScope()

    Column() {

    }
}


