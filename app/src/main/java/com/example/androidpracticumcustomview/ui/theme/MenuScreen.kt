package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androidpracticumcustomview.R

@Composable
fun MenuScreen(
    composeClick: () -> Unit,
    tradeVersClick: () -> Unit,
) {
    val modifier = Modifier
        .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
        .padding(8.dp)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    )
    {
       Text(
           text = stringResource(id = R.string.compose),
           modifier = modifier.clickable(onClick = composeClick)
       )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.custom_view),
            modifier = modifier.clickable(onClick = tradeVersClick)
        )
    }
}