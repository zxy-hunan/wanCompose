package com.zxy_hunan.compose.ui.home.Structure

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowRow
import com.zxy_hunan.compose.R
import com.zxy_hunan.compose.acy.widget.LabelTextButton
import com.zxy_hunan.compose.acy.widget.ListTitle
import com.zxy_hunan.compose.entity.ParentBean
import com.zxy_hunan.compose.ui.theme.grey1

/**
 * 体系
 */
@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StructureTreePage(navCtrl: NavHostController, viewModel: StructureVM = hiltViewModel()) {

    viewModel.start()

    val systemData by remember { viewModel.list }
    val currentPosition by remember { viewModel.currentListIndex }
    val listState = rememberLazyListState(currentPosition)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White),
        state = listState, contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        systemData.forEachIndexed { position, chapter ->
            stickyHeader { ListTitle(title = chapter.name ?: "") }
            item {
                StructreItem(bean = chapter,onSelect = {
                    parent ->
                    viewModel.savePosition(listState.firstVisibleItemIndex)

                })
                if (position <=systemData.size-1){
                    Divider(startIndent = 10.dp,color = grey1,thickness = 0.8f.dp)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun StructreItem(
    bean: ParentBean,
    isLoading: Boolean = false,
    onSelect: (parent: ParentBean) -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)) {
        if (isLoading){
            ListTitle(title = "i am title",isLoading = true)
            FlowRow(modifier = Modifier.padding(horizontal = 10.dp)) {
                for (i in 0..7){
                    LabelTextButton(text = "Android"
                    ,modifier = Modifier
                            .padding(start = 5.dp, bottom = 5.dp)
                    ,isLoding = true)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }else{
            if (!bean.children.isNullOrEmpty()){
                FlowRow(modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                    for (item in bean.children!!){
                        LabelTextButton(text = item.name ?: "Android"
                        ,modifier = Modifier.padding(start = 5.dp,bottom = 5.dp),
                        onClick = {
                            onSelect(item)
                        })
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}