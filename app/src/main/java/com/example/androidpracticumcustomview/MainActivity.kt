package com.example.androidpracticumcustomview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import com.example.androidpracticumcustomview.ui.theme.CustomContainer
import com.example.androidpracticumcustomview.ui.theme.MainScreen

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Раскомментируйте нужный вариант
         */
//        startXmlPracticum() // «традиционный» android (XML)
        setContent { // Jetpack Compose
            MainScreen()
        }

//        private fun startXmlPracticum() {
//            val customContainer = CustomContainer(this)
//
//
//            val firstView = TextView(this).apply {
//                text = "UP"
//                textSize = 24f
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//
//            }
//
//            val secondView = TextView(this).apply {
//                text = "DOWN"
//                textSize = 24f
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//            }
//            customContainer.addView(firstView)
//            customContainer.addView(secondView)
//            setContentView(customContainer)
//
//            // Добавление второго элемента через некоторое время
////        Handler(Looper.getMainLooper()).postDelayed({
////            customContainer.addView(secondView)
////        }, 2000)
//        }
    }
}