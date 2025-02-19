package com.example.androidpracticumcustomview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import com.example.androidpracticumcustomview.ui.theme.CustomContainer
import com.example.androidpracticumcustomview.ui.theme.MainScreen
import com.example.androidpracticumcustomview.ui.theme.MenuScreen

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    private var flagOfMenuScreen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun startMenuScreen() {
            if (!flagOfMenuScreen){
                flagOfMenuScreen = true
                setContent {
                    MenuScreen(
                        composeClick = {
                            flagOfMenuScreen = false
                            setContent{
                                MainScreen()
                            }
                        },
                        tradeVersClick = {
                            flagOfMenuScreen = false
                            startXmlPracticum()
                        }
                    )
                }
            } else {
                finish()
            }
        }

        val back = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startMenuScreen()
            }
        }

        onBackPressedDispatcher.addCallback(this, back)

        startMenuScreen()
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)

        val firstView = TextView(this).apply {
            text = context.getString(R.string.up)
            textSize = 24f
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val secondView = TextView(this).apply {
            text = context.getString(R.string.down)
            textSize = 24f
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        customContainer.addView(firstView)
        customContainer.addView(secondView)
        setContentView(customContainer)
    }
}
