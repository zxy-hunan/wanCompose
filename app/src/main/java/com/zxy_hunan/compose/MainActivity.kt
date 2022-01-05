package com.zxy_hunan.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.zxy_hunan.compose.acy.route.RouteName
import com.zxy_hunan.compose.acy.widget.BottomNavBarView

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                    HomeScaffold()
        }
    }
}

@Composable
fun HomeScaffold(){
    /**NavController 是 Navigation 组件的中心 API。此 API 是有状态的，可以跟踪组成应用屏幕的可组合项的返回堆栈以及每个屏幕的状态。
    您可以通过在可组合项中使用 rememberNavController() 方法来创建 NavController：
    您应该在可组合项层次结构中的适当位置创建 NavController，使所有需要引用它的可组合项都可以访问它。这遵循状态提升的原则，
    并且允许您使用 NavController 及其通过 currentBackStackEntryAsState() 提供的状态作为更新屏幕外的可组合项的可信来源。有**/
    val navController= rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination =navBackStackEntry?.destination
    val scaffoldState= rememberScaffoldState()

    Scaffold(modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
        bottomBar = {
                    when(currentDestination?.route){
                        RouteName.HOME -> BottomNavBarView(navCtrl = navController)
                        RouteName.MOVIE -> BottomNavBarView(navCtrl = navController)
                        RouteName.CHAT -> BottomNavBarView(navCtrl = navController)
                        RouteName.ME -> BottomNavBarView(navCtrl = navController)
                    }
    },content = {
            var homeIndex = remember { 0 }
            var categoryIndex = remember { 0 }

            NavHost(
                modifier = Modifier.background(color = colorResource(id = R.color.orange)),
                navController = navController,
                startDestination = RouteName.HOME
            ){
                //首页
                composable(route = RouteName.HOME) {
                }
            }
        },snackbarHost = {
        SnackbarHost(hostState = scaffoldState.snackbarHostState){
            data ->
        }
    })
}
