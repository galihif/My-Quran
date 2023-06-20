package com.giftech.myquran.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    @StringRes text:Int,
    modifier: Modifier = Modifier
) {
    Text(
        stringResource(text),
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
}

@Composable
fun TextTitle(
    text:String,
    modifier: Modifier = Modifier,
    fontSize:TextUnit = 24.sp
) {
    Text(
        text,
        modifier = modifier,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize
    )
}

