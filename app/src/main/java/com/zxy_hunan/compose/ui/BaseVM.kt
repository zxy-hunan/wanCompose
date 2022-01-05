package com.zxy_hunan.compose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseVM<T> : ViewModel() {
    var list = mutableStateOf(mutableListOf<T>())
}