package com.giftech.myquran.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftech.myquran.R
import com.giftech.myquran.ui.components.BoxLastRead
import com.giftech.myquran.ui.components.TextTitle
import com.giftech.myquran.ui.theme.MyQuranTheme

@Composable
fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextTitle(text = R.string.title)
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
            Text(
                text = stringResource(id = R.string.salam),
                Modifier.padding(vertical = 16.dp),
                fontSize = 18.sp
            )
            BoxLastRead(
                surah = "Al-Baqarah",
                ayat = 8,
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    MyQuranTheme {
        HomeScreen()
    }
}