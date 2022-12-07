package com.giftech.myquran.ui.screen.surah

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giftech.myquran.ui.components.BoxSurahHeader
import com.giftech.myquran.ui.components.TitleBar
import com.giftech.myquran.ui.theme.MyQuranTheme

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
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            BoxSurahHeader()
        }
    }
}

@Preview
@Composable
fun SurahScreenPreview() {
    MyQuranTheme {
        SurahScreen()
    }
}