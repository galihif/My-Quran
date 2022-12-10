package com.giftech.myquran.ui.screen.surah

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.myquran.data.model.Ayat
import com.giftech.myquran.data.model.LastRead
import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.ui.components.BoxSurahHeader
import com.giftech.myquran.ui.components.CardAyatHeader
import com.giftech.myquran.ui.components.TitleBar
import com.giftech.myquran.ui.theme.*
import com.giftech.myquran.utils.Resource
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SurahScreen(
    viewModel: SurahViewModel = hiltViewModel(),
    nomorSurah: Int = 1,
    onBack: () -> Unit
) {
    val namaSurah = remember {
        viewModel.namaSurah
    }.collectAsState()
    val surah = remember {
        viewModel.surah
    }.collectAsState()
    var isLastReadSurah by remember {
        mutableStateOf(false)
    }
    val lastRead = remember {
        viewModel.lastRead
    }.collectAsState()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(isLastReadSurah) {
        if (isLastReadSurah) {
            coroutineScope.launch {
                listState.animateScrollToItem(lastRead.value.nomorAyat)
            }
        }
    }

    val isAudioStarted = remember {
        viewModel.isAudioStarted
    }
    val isAudioPlayed = remember {
        viewModel.isAudioPlayed
    }

    Scaffold(
        topBar = {
            TitleBar(
                title = namaSurah.value,
                onBack = {
                    onBack()
                    viewModel.stopAudio()
                }
            )
        }
    ) {
        viewModel.setNomorSurah(nomorSurah)
        surah.value.let {
            when (it) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val surahRes = it.data
                    if (surahRes != null) {
                        SurahContent(
                            listState,
                            surah = surahRes,
                            onSavedClick = { nomorAyat ->
                                viewModel.setLastRead(
                                    LastRead(
                                        nomorAyat = nomorAyat,
                                        nomorSurah = nomorSurah,
                                        namaSurah = namaSurah.value
                                    )
                                )
                            },
                            lastRead = lastRead.value,
                            isLastReadSurah = isLastReadSurah,
                            isPlayed = isAudioPlayed.value,
                            onClickPlay = {
                                isAudioPlayed.value = !isAudioPlayed.value
                                if (!isAudioStarted.value){
                                    isAudioStarted.value = true
                                    viewModel.playAudio(surahRes.audio)
                                } else{
                                    viewModel.resumeOrPauseAudio()
                                }
                            }
                        )
                        isLastReadSurah = surahRes.nomor == lastRead.value.nomorSurah
                        viewModel.setNamaSurah(surahRes.nama)
                    }
                }
            }
        }
    }
}

@Composable
fun SurahContent(
    listState: LazyListState,
    surah: Surah,
    lastRead: LastRead,
    isLastReadSurah: Boolean,
    onSavedClick: (Int) -> Unit,
    isPlayed:Boolean,
    onClickPlay:()->Unit
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            BoxSurahHeader(
                surah,
                isPlayed,
                onClickPlay
            )
        }
        item {
            Spacer(Modifier.height(16.dp))
        }
        items(surah.listAyat!!) {
            AyatItem(
                it,
                isLastReadSurah && lastRead.nomorAyat == it.nomor,
                onSavedClick = { nomorAyat ->  onSavedClick(nomorAyat) }
            )
        }
    }
}

@Composable
fun AyatItem(
    ayat: Ayat,
    isSaved: Boolean,
    onSavedClick: (Int) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        CardAyatHeader(
            ayat.nomor,
            isSaved,
            onSaveClick = { onSavedClick(ayat.nomor) }
        )
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
        SurahScreen {}
    }
}