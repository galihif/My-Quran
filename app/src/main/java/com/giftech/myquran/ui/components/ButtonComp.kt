package com.giftech.myquran.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.giftech.myquran.ui.theme.Peach200

@Composable
fun GetStartedButton(
    @StringRes text:Int,
    modifier: Modifier = Modifier,
    onClick:()->Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Peach200),
        onClick = onClick,
    ) {
        Text(stringResource(text), color = Color.White)
    }
}