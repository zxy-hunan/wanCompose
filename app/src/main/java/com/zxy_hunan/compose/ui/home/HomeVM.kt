package com.zxy_hunan.compose.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zxy_hunan.compose.entity.TabTitle
import com.zxy_hunan.compose.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeVM : BaseVM<TabTitle>() {
    val titles = mutableStateOf(
        mutableListOf(
            TabTitle(101, "推荐"),
            TabTitle(101, "推荐"),
            TabTitle(101, "推荐"),
            TabTitle(101, "推荐")
        )
    )

    override fun onCleared() {
        super.onCleared()
    }
}