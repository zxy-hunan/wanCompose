package com.zxy_hunan.compose.acy.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.KeyboardType
import com.zxy_hunan.compose.ui.theme.black
import com.zxy_hunan.compose.ui.theme.black3

@Composable
private fun BasicLabelEditView(
    modifier: Modifier =Modifier,
    text:String,
    labelText:String,
    labelTextColor:Color= black3,
    hintText:String="",
    deleteIconColor: Color= black,
    onValueChanged:(String) ->Unit,
    onDeleteClick:() ->Unit,
    inputCursorColor: Color= black,
    inputTextColor:Color= black,
    borderColor: Color= black3,
    keyboardType:KeyboardType=KeyboardType.Text,
    isHideKeyboard:Boolean=true
){
    val keyboardService= LocalTextInputService.current

}