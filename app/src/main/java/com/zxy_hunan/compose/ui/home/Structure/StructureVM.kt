package com.zxy_hunan.compose.ui.home.Structure

import com.zxy_hunan.compose.entity.ParentBean
import com.zxy_hunan.compose.http.HttpRepository
import com.zxy_hunan.compose.http.HttpResult
import com.zxy_hunan.compose.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class StructureVM @Inject constructor(private var repo: HttpRepository) : BaseVM<ParentBean>() {
    override fun start() {
        initThat { loadContent() }
    }

    fun loadContent() {
        async {
            repo.getStructureList().collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        list.value = response.result
                    }
                    is HttpResult.Fail -> {

                    }
                }
            }
        }
    }
}