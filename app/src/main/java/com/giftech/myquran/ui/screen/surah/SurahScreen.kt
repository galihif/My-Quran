package com.giftech.myquran.ui.screen.surah

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftech.myquran.ui.components.BoxSurahHeader
import com.giftech.myquran.ui.components.CardAyatHeader
import com.giftech.myquran.ui.components.TitleBar
import com.giftech.myquran.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SurahScreen() {
    Scaffold(
        topBar = {
            TitleBar(
                title = "{Nama Surah}",
                onBack = {}
            )
        }
    ) {
        SurahContent()
    }
}

@Composable
fun SurahContent() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            BoxSurahHeader()
        }
        item{
            Spacer(Modifier.height(16.dp))
        }
        items((0..10).map { it.toString() }){
            AyatItem(it)
        }
    }
}

@Composable
fun AyatItem(ayat: String) {
    Column(Modifier.fillMaxWidth()) {
        CardAyatHeader(ayat.toInt())
        Text(
            "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ",
            Modifier.align(Alignment.End),
            textAlign = TextAlign.End,
            color = Purple900,
            fontFamily = fontsArab,
            fontSize = 18.sp
        )
        Text(
            "{Surah Arti}",
            fontSize = 16.sp,
            color = Purple900,
        )
    }
}

@Preview
@Composable
fun SurahScreenPreview() {
    MyQuranTheme {
        SurahScreen()
    }
}