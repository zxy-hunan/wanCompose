package com.zxy_hunan.compose.acy

import android.content.Intent
import android.os.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.zxy_hunan.compose.MainActivity
import com.zxy_hunan.compose.R
import com.zxy_hunan.compose.ui.theme.ComposePracticeTheme

class WelcomeAcy : ComponentActivity() {
    private val mHandler=object:Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            startActivity(Intent(this@WelcomeAcy,MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    loadImage()
                    mHandler.sendEmptyMessageDelayed(0,5000)
                }
            }
        }
    }

    @Composable
    fun loadImage() {
        Column(
            Modifier
                .background(color = Color.Companion.White)
                .fillMaxHeight(1f)
                .fillMaxWidth(1f),Arrangement.Center,Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.mipmap.ic_icon), contentDescription = "icon")
        }
    }
}