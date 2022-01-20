package com.zxy_hunan.compose.ui.home

import androidx.compose.runtime.mutableStateOf
import com.zxy_hunan.compose.entity.TabTitle
import com.zxy_hunan.compose.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(): BaseVM<TabTitle>() {
    val titles = mutableStateOf(
        mutableListOf(
            TabTitle(101, "推荐"),
            TabTitle(101, "体系"),
            TabTitle(101, "问答"),
            TabTitle(101, "公众号")
        )
    )

    override fun onCleared() {
        super.onCleared()
    }

    override fun start() {

    }
}