package com.giftech.myquran.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.myquran.R
import com.giftech.myquran.data.Resource
import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.ui.components.BoxLastRead
import com.giftech.myquran.ui.components.TextTitle
import com.giftech.myquran.ui.theme.Gray500
import com.giftech.myquran.ui.theme.MyQuranTheme
import com.giftech.myquran.ui.theme.fontsArab

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold {
        viewModel.listSurah.collectAsState().value.let {
            when(it){
                is Resource.Error -> {
                    Log.d("TAG", "HomeScreen: ${it.message}")
                }
                is Resource.Loading -> {
                    Log.d("TAG", "HomeScreen: Loading")
                }
                is Resource.Success -> {
                    if (it.data != null){
                        HomeContent(listSurah = it.data)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeContent(listSurah:List<Surah>) {
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
                    surah = "Al Baqarah",
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
        items(listSurah) {
            SurahItem(it)
        }
    }
}

@Composable
fun SurahItem(surah: Surah) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.wrapContentSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_bg_surah_number),
                contentDescription = ""
            )
            Text(surah.nomor.toString(), Modifier.align(Alignment.Center))
        }
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = surah.nama,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Mekah ${surah.jumlahAyat} ayat",
                color = Gray500
            )
        }
        Text(
            text = surah.asma,
            color = MaterialTheme.colors.primary,
            fontFamily = fontsArab,
            fontSize = 22.sp
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