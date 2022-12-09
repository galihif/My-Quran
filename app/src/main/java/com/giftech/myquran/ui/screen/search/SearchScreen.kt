package com.giftech.myquran.ui.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.ui.components.SearchBar
import com.giftech.myquran.ui.components.SurahItem
import com.giftech.myquran.ui.theme.MyQuranTheme

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onSurahClicked: (Int) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val keyword = remember{
        viewModel.keyword
    }
    val searchResult = remember {
        viewModel.listSurah
    }.collectAsState()

    Scaffold(
        topBar = {
            SearchBar(
                keyword = keyword.value,
                onBack = onBack,
                onValueChange = {
                    viewModel.setKeyword(it)
                }
            )
        }
    ) {
        SearchResult(
            listSurah = searchResult.value,
            onSurahClicked = onSurahClicked
        )
    }
}

@Composable
fun SearchResult(
    listSurah: List<Surah>,
    onSurahClicked: (Int) -> Unit,
) {
    LazyColumn(
        Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        items(listSurah) {
            SurahItem(
                surah = it,
                onSurahClicked = onSurahClicked
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun SearchPreview() {
    MyQuranTheme {
        SearchScreen()
    }
}