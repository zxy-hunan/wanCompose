package com.zxy_hunan.compose.acy.widget

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zxy_hunan.compose.R
import com.zxy_hunan.compose.acy.route.BottomNavRoute
import com.zxy_hunan.compose.entity.TabTitle
import com.zxy_hunan.compose.theme.SearchBarHeight
import com.zxy_hunan.compose.theme.TabBarHeight

@Composable
fun BottomNavBarView(navCtrl: NavHostController) {
    val bottomNavList = mutableListOf<BottomNavRoute>(
        BottomNavRoute.Home,
        BottomNavRoute.Movie,
        BottomNavRoute.Chat,
        BottomNavRoute.Me
    )
    BottomNavigation {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavList.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.background(color = colorResource(id = R.color.purple_700)),
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = null)
                },
                label = { Text(text = stringResource(id = screen.stringId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                onClick = {
                    if (currentDestination?.route != screen.routeName) {
                        navCtrl.navigate(screen.routeName) {
                            popUpTo(navCtrl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }

    }
}


@Composable
fun HomeSearchBar(
    onUserIconClick: () -> Unit,
    onSearchClick: () -> Unit,
    onRightIconClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchBarHeight)
            .background(colorResource(id = R.color.purple_700))
    ) {
        Image(painter = painterResource(id = R.mipmap.ic_icon), contentDescription = "User",
            contentScale = ContentScale.Crop, modifier = Modifier
                .padding(start = 10.dp)
                .width(28.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(14.dp))
                .align(alignment = Alignment.CenterVertically)
                .clickable {
                    onUserIconClick.invoke()
                })

        Row(modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(25.dp)
            .align(alignment = Alignment.CenterVertically)
            .weight(1f)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(12.5.dp)
            )
            .clickable { onSearchClick() }) {
            Icon(
                painter = painterResource(id = R.mipmap.ic_search), contentDescription = "搜索",
                modifier = Modifier
                    .size(25.dp)
                    .padding(end = 10.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
            }
        }

        Icon(painter = painterResource(id = R.mipmap.ic_add),
            contentDescription = "发表",
            modifier = Modifier
                .size(34.dp)
                .align(alignment = Alignment.CenterVertically)
                .padding(end = 10.dp)
                .clickable { onRightIconClick.invoke() })
    }

}

@Composable
fun TextTabBar(
    index: Int,
    tabTexts: MutableList<TabTitle>,
    modifier: Modifier = Modifier,
    bgColor: Color = colorResource(id = R.color.purple_700),
    contentColor: Color = colorResource(id = R.color.white),
    onTabSelected: ((index: Int) -> Unit)? = null,
    withAdd: Boolean = false,
    onAddClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(TabBarHeight)
            .background(bgColor)
            .horizontalScroll(state = rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            tabTexts.forEachIndexed { i, tabTitle ->
                Text(text = tabTitle.text,
                    fontSize = if (index == i) 20.sp else 15.sp,
                    fontWeight = if (index == i) FontWeight.SemiBold else FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp)
                        .clickable {
                            if (onTabSelected != null) {
                                onTabSelected(i)
                            }
                        },
                    color = contentColor
                )
            }

        }

        if (withAdd) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { onAddClick.invoke() })
        }

    }
}

