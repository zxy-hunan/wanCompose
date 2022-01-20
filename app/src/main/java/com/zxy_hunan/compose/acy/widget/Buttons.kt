package com.zxy_hunan.compose.acy.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.material.placeholder
import com.zxy_hunan.compose.R
import com.zxy_hunan.compose.ui.theme.Purple700
import com.zxy_hunan.compose.ui.theme.grey3
import com.zxy_hunan.compose.ui.theme.white1

@ExperimentalFoundationApi
@Composable
fun LabelTextButton(
    text: String, modifier: Modifier = Modifier, isSelect: Boolean = true,
    specTextColor: Color? = null, cornerCalue: Dp = 25.dp / 2, isLoding: Boolean = false,
    onClick: (() -> Unit)? = null, onLongClick: (() -> Unit)? = null
) {

    Text(
        text = text,
        modifier = modifier
            .height(25.dp)
            .clip(shape = RoundedCornerShape(cornerCalue))
            .background(color = colorResource(id = R.color.purple_700))
            .padding(horizontal = 10.dp, vertical = 3.dp)
            .combinedClickable(enabled = !isLoding,
                onClick = { onClick?.invoke() },
                onLongClick = { onLongClick?.invoke() })
            .placeholder(visible = isLoding, color = grey3),
        fontSize = 13.sp,
        textAlign = TextAlign.Center,
        color = specTextColor ?: if (isSelect) white1 else grey3,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}