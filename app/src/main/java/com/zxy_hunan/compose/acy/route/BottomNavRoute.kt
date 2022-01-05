package com.zxy_hunan.compose.acy.route

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.zxy_hunan.compose.R

sealed class BottomNavRoute (var routeName:String,@StringRes var stringId:Int,
       var icon:ImageVector){
    object Home:BottomNavRoute(RouteName.HOME, R.string.home,Icons.Default.Home)
    object Movie:BottomNavRoute(RouteName.MOVIE, R.string.movie,Icons.Default.Menu)
    object Chat:BottomNavRoute(RouteName.CHAT, R.string.chat,Icons.Default.Call)
    object Me:BottomNavRoute(RouteName.ME, R.string.me,Icons.Default.Person)
}