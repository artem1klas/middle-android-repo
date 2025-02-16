package com.example.androidpracticumcustomview.ui.theme

import android.content.Context
import android.provider.SyncStateContract.Constants
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children
import com.example.androidpracticumcustomview.ui.DURATION_OF_ALPHA
import com.example.androidpracticumcustomview.ui.DURATION_OF_MOVEMENT
import com.example.androidpracticumcustomview.ui.MAX_CHILD

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    init {
        setWillNotDraw(false)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        for(child in children){
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val centerHorizontal = (right - left) / 2
        val centerVertical = (bottom - top) / 2

        for (child in children) {
            val leftEdge = centerHorizontal - (child.measuredWidth / 2)
            val topEdge = centerVertical - (child.measuredHeight / 2)

            child.layout(
                leftEdge,
                topEdge,
                leftEdge + child.measuredWidth,
                topEdge + child.measuredHeight
            )

            child.alpha = 0f
            child.animate().alpha(1f).setDuration(DURATION_OF_MOVEMENT)

            when(children.indexOf(child)){
                0 -> child.animate().y(top.toFloat()).setDuration(DURATION_OF_MOVEMENT).start()
                1 -> child.animate().y(bottom.toFloat() - child.measuredHeight).setDuration(DURATION_OF_MOVEMENT).start()
            }
        }

    }

    override fun addView(child: View) {
        if (childCount >= MAX_CHILD) {
            throw IllegalStateException("Превышено количество дочерних View!")
        }
        super.addView(child)
    }
}