package com.giftech.myquran.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.giftech.myquran.R

@Composable
fun BoxLastRead(
    modifier: Modifier = Modifier,
    surah:String,
    ayat:Int,
    onClick:()->Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bg_last_read),
            contentDescription = "",
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            Modifier
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row() {
                Icon(
                    painterResource(R.drawable.ic_last_read),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Last Read",
                    color = Color.White
                )
            }
            Text(
                text = surah,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Ayat $ayat",
                color = Color.White
            )

        }
    }
}