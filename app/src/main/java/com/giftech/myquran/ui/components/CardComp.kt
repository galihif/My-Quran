package com.giftech.myquran.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.twotone.Bookmark
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftech.myquran.ui.theme.Purple100

@Composable
fun CardNomorAyat(nomor: Int) {
    Card(
        Modifier.size(28.dp),
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(Modifier.fillMaxSize()) {
            Text(
                "$nomor",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun CardAyatHeader(
    nomor: Int,
    isSaved:Boolean,
    onSaveClick:()->Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Purple100,
        elevation = 0.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardNomorAyat(nomor)
            IconButton(onClick = onSaveClick) {
                Crossfade(targetState = isSaved) {
                    Icon(
                        imageVector =  if(it) Icons.Filled.Bookmark else Icons.TwoTone.Bookmark,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}