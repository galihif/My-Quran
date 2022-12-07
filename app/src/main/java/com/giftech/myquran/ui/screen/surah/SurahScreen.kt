package com.giftech.myquran.ui.screen.surah

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.myquran.data.Resource
import com.giftech.myquran.data.model.Ayat
import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.ui.components.BoxSurahHeader
import com.giftech.myquran.ui.components.CardAyatHeader
import com.giftech.myquran.ui.components.TitleBar
import com.giftech.myquran.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SurahScreen(
    viewModel: SurahViewModel = hiltViewModel(),
    nomorSurah: Int = 1,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TitleBar(
                title = viewModel.namaSurah.collectAsState().value,
                onBack = onBack
            )
        }
    ) {
        viewModel.setNomorSurah(nomorSurah)
        viewModel.surah.collectAsState().value.let {
            when (it) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val data = it.data
                    if (data != null) {
                        SurahContent(data)
                        viewModel.setNamaSurah(data.nama)
                    }
                }
            }
        }
    }
}

@Composable
fun SurahContent(
    surah: Surah
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            BoxSurahHeader(surah)
        }
        item {
            Spacer(Modifier.height(16.dp))
        }
        items(surah.listAyat!!) {
            AyatItem(it)
        }
    }
}

@Composable
fun AyatItem(ayat: Ayat) {
    Column(Modifier.fillMaxWidth()) {
        CardAyatHeader(ayat.nomor)
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                ayat.arab,
                Modifier.align(Alignment.End),
                textAlign = TextAlign.End,
                color = Purple900,
                fontFamily = fontsArab,
                fontSize = 18.sp
            )
            Text(
                ayat.arti,
                fontSize = 16.sp,
                color = Purple900,
            )
        }
    }
}

@Preview
@Composable
fun SurahScreenPreview() {
    MyQuranTheme {
        SurahScreen() {}
    }
}