package com.zxy_hunan.compose.acy.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.placeholder.material.placeholder
import com.zxy_hunan.compose.R
import com.zxy_hunan.compose.entity.Article
import com.zxy_hunan.compose.entity.WebData
import com.zxy_hunan.compose.theme.H6
import com.zxy_hunan.compose.theme.ListTitleHeight
import com.zxy_hunan.compose.ui.theme.black3
import com.zxy_hunan.compose.ui.theme.white1
import com.zxy_hunan.compose.ui.theme.white3
import com.zxy_hunan.compose.ui.util.RegexUtils

@ExperimentalComposeUiApi
@Composable
fun MultiStateItemView(
    modifier: Modifier = Modifier, data: Article, isTop: Boolean = false,
    onSelected: (data: WebData) -> Unit = {},
    onCollectClick: (articleId: Int) -> Unit = {},
    onUserClick: (userId: Int) -> Unit = {},
    isLoading: Boolean = false
) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .background(color = Color.White, RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .clickable(enabled = !isLoading) {
                onSelected.invoke(WebData(data.title!!, data.link!!))
            },
        shape = RoundedCornerShape(4.dp),
//        backgroundColor = Color.White
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(20.dp)
                .background(color = Color.White)
        ) {
            val (circleText, name, publishIcon, publishTime, title, chip1, chip2, tag, favourite) = createRefs()
            Text(text = getFirstCharFromName(data), modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .clip(RoundedCornerShape(20.dp / 2))
                .background(color = colorResource(id = R.color.purple_700))
                .constrainAs(circleText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .placeholder(
                    visible = isLoading,
                    color = white3
                ),
                textAlign = TextAlign.Center,
                fontSize = H6,
                color = white1
            )
            val titleModifier =
                if (isLoading) Modifier.width(80.dp) else Modifier.wrapContentWidth()
            MediumTitle(title = getAuthorName(data), modifier =
            titleModifier
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(circleText.end)
                }
                .padding(start = 5.dp)
                .clickable {
                    onUserClick.invoke(data.userId)
                }
                .pointerInteropFilter { false }, isLoading = isLoading
            )
            val dateModifier = if (isLoading) Modifier.width(80.dp) else Modifier.wrapContentWidth()
            MinTitle(text = RegexUtils().timestamp(data.niceDate!!) ?: "1970-1-1",
                modifier = dateModifier
                    .constrainAs(publishTime) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }, isLoading = isLoading
            )
            TimerIcon(
                modifier = Modifier
                    .padding(end = if (isLoading) 5.dp else 0.dp)
                    .constrainAs(publishIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(publishTime.start)
                        bottom.linkTo(publishTime.bottom)
                    },
                isLoading = isLoading
            )

            TextContent(text = data.title ?: "", maxLine = 3, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 10.dp, bottom = 20.dp)
                .constrainAs(title) {
                    top.linkTo(circleText.bottom)
                    end.linkTo(parent.end)
                }, isLoading = isLoading
            )


        }

    }

}

@Composable
fun ListTitle(
    modifier: Modifier = Modifier, title: String, subTitle: String = "",
    isLoading: Boolean = false, onSubtitleClick: () -> Unit = {}
) {
    Row(modifier = modifier
        .placeholder(false)
        .fillMaxWidth()
        .height(ListTitleHeight)
        .background(color = Color.White)) {

        Box(modifier = Modifier
            .padding(horizontal = 10.dp)
            .width(5.dp)
            .height(16.dp)
            .align(Alignment.CenterVertically)
            .background(color = black3)
        )

        MediumTitle(title = title, modifier = Modifier.align(Alignment.CenterVertically),
        isLoading = isLoading)

        Spacer(modifier = Modifier.weight(1f))

        TextContent(text = subTitle, modifier = Modifier
            .padding(end = 10.dp)
            .clickable {
                onSubtitleClick.invoke()
            },isLoading = isLoading)
    }
}


fun getAuthorName(data: Article?): String {
    return if (data?.shareUser.isNullOrEmpty()) {
        data?.author ?: ""
    } else {
        data?.shareUser ?: ""
    }
}

fun getFirstCharFromName(data: Article?): String {
    val author = getAuthorName(data)
    return if (author.isNotEmpty()) author.trim().substring(0, 1) else "?"
}
