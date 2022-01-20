package com.zxy_hunan.compose.acy.widget

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.google.accompanist.placeholder.material.placeholder
import com.zxy_hunan.compose.theme.H5
import com.zxy_hunan.compose.theme.H6
import com.zxy_hunan.compose.theme.H7
import com.zxy_hunan.compose.ui.theme.black3
import com.zxy_hunan.compose.ui.theme.grey1
import com.zxy_hunan.compose.ui.theme.white3
import kotlin.math.max

@Composable
fun MediumTitle(
    title: String, modifier: Modifier = Modifier, color: Color = black3,
    textAlign: TextAlign = TextAlign.Start, isLoading: Boolean = false
) {
    Title(
        title = title, fontSize = H5, modifier = modifier, color = color,
        textAlign = textAlign, isLoading = isLoading
    )
}

@Composable
fun MinTitle(
    text: String, modifier: Modifier = Modifier, color: Color = grey1, maxLine: Int = 1,
    textAlign: TextAlign = TextAlign.Start, isLoading: Boolean = false
) {
    Title(
        title = text, fontSize = H7, modifier = modifier, color = color,
        maxLine = maxLine, textAlign = textAlign, isLoading = isLoading
    )
}


@Composable
fun TextContent(text:String,modifier: Modifier=Modifier,color: Color= grey1,maxLine: Int=99
,textAlign: TextAlign= TextAlign.Start,canCopy:Boolean=false,isLoading: Boolean=false){
    if (canCopy){
        SelectionContainer {
            Title(title = text, fontSize = H6,modifier = modifier,color = color,
            maxLine = maxLine,textAlign = textAlign,isLoading = isLoading)
        }
    }else{
        Title(title = text, fontSize = H6,modifier = modifier,color = color,
            maxLine = maxLine,textAlign = textAlign,isLoading = isLoading)
    }
}


@Composable
fun Title(
    title: String, modifier: Modifier = Modifier,
    fontSize: TextUnit, color: Color = grey1,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLine: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    isLoading: Boolean = false
) {
    Text(
        text = title,
        modifier = modifier.placeholder(visible = isLoading, color = white3),
        fontSize = fontSize,
        color = color,
        maxLines = maxLine,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}