package com.giftech.myquran.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftech.myquran.R
import com.giftech.myquran.ui.components.BoxLastRead
import com.giftech.myquran.ui.components.TextTitle
import com.giftech.myquran.ui.theme.Gray500
import com.giftech.myquran.ui.theme.MyQuranTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold {
        val itemList = (0..10).map { it.toString() }
        LazyColumn(
            Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Column {
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
                    TextTitle(
                        text = R.string.surah,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 8.dp)
                    )
                }
            }
            items(itemList) {
                SurahItem(it)
            }
        }
    }
}

@Composable
fun SurahItem(name: String) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.wrapContentSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_bg_surah_number),
                contentDescription = ""
            )
            Text(name, Modifier.align(Alignment.Center))
        }
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = "Surah Name",
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Mekah {2} ayat",
                color = Gray500
            )
        }
        Text(
            text = "Arabic Name",
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    MyQuranTheme {
        HomeScreen()
    }
}