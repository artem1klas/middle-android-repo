package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.Layout
import com.example.androidpracticumcustomview.ui.DURATION_OF_ALPHA
import com.example.androidpracticumcustomview.ui.DURATION_OF_MOVEMENT
import kotlinx.coroutines.launch

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */
@Composable
fun CustomContainerCompose(
    firstChild: @Composable (() -> Unit)?,
    secondChild: @Composable (() -> Unit)?
) {

    var heightOfScreen by remember { mutableStateOf(0f) }

    val alpha = remember { Animatable(0f) }
    val offsetVertical = remember { Animatable(0f) }



    // Блок активации анимации при первом запуске
    LaunchedEffect(Unit) {
        launch {
            offsetVertical.animateTo(
                targetValue = heightOfScreen / 2,
                animationSpec = tween(DURATION_OF_MOVEMENT.toInt())
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(DURATION_OF_ALPHA.toInt())
            )
        }
    }

    // Основной контейнер
    Box(
        modifier = Modifier.alpha(alpha.value)
    ) {
        Layout(
            content = {
                firstChild?.let { Box { it() } }
                secondChild?.let { Box { it() } }
            }
        ) { measurables, consraints ->

            val placeables = measurables.map { measurable -> measurable.measure(consraints) }

            layout(consraints.maxWidth, consraints.maxHeight) {
                placeables.forEachIndexed { index, placeable ->
                    val x = consraints.maxWidth / 2 - placeable.width / 2
                    val y = consraints.maxHeight / 2 - placeable.height / 2
                    val targetY = when (index) {
                        0 -> {
                            heightOfScreen = consraints.maxHeight.toFloat() + placeable.height * 2f
                            -offsetVertical.value.toInt()
                        }
                        else -> {
                            heightOfScreen = consraints.maxHeight.toFloat() - placeable.height * 2f
                            offsetVertical.value.toInt()
                        }
                    }
                    placeable.placeRelative(x = x, y = y + targetY)
                }
            }

        }
    }
}