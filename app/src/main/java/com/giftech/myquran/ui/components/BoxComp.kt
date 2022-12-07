package com.giftech.myquran.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.unit.sp
import com.giftech.myquran.R
import com.giftech.myquran.data.model.Surah

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

@Composable
fun BoxSurahHeader(surah: Surah) {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(R.drawable.bg_surah_detail),
            contentDescription = "",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                surah.nama,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Text(
                surah.arti,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Divider(
                Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp),
                thickness = 0.5.dp,
                color = Color.White
            )
            Text(
                "${surah.type} - ${surah.jumlahAyat} ayat".uppercase(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Image(
                painterResource(R.drawable.ic_basmalah),
                contentDescription ="",
            )
            Spacer(Modifier.height(8.dp))
            IconButton(onClick = {}) {
                Image(
                    painterResource(R.drawable.ic_play),
                    contentDescription =""
                )
            }
        }
    }
}