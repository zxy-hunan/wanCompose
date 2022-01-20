package com.zxy_hunan.compose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseVM<T> : ViewModel() {

    private var _isInited = mutableStateOf(false)

    var list = mutableStateOf(mutableListOf<T>())
    var currentListIndex = mutableStateOf(0)

    private val isInited: Boolean
        get() = _isInited.value

    private fun requestInitialized() {
        _isInited.value = true
    }

    fun resetInitState() {
        _isInited.value = false
    }

    abstract fun start()

    fun initThat(block: () -> Unit) {
        if (!isInited) {
            block.invoke()
            requestInitialized()
        }
    }


    fun async(block: suspend ()-> Unit) {
        viewModelScope.launch { block() }
    }

    fun savePosition(index: Int) {
        currentListIndex.value = index
        println("## save position = $index ##")
    }
}