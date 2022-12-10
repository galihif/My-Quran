package com.giftech.myquran.ui.screen.surah

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.myquran.data.MainRepository
import com.giftech.myquran.data.model.LastRead
import com.giftech.myquran.data.model.Surah
import com.giftech.myquran.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private var _nomorSurah = MutableStateFlow(1)

    fun setNomorSurah(nomor:Int){
        _nomorSurah.value = nomor
        getDetailSurah()
    }

    private val _surah = MutableStateFlow<Resource<Surah>>(Resource.Loading())
    val surah:StateFlow<Resource<Surah>> = _surah

    private fun getDetailSurah(){
        viewModelScope.launch {
            repository.getDetailSurah(_nomorSurah.value)
                .catch {
                    _surah.value = Resource.Error(it.message.toString())
                }.collect{
                    _surah.value = Resource.Success(it)
                }
        }
    }

    private val _namaSurah = MutableStateFlow("")
    val namaSurah:StateFlow<String> = _namaSurah

    fun setNamaSurah(nama:String){
        _namaSurah.value = nama
    }

    private val _lastRead:MutableStateFlow<LastRead> = MutableStateFlow(LastRead())
    val lastRead:StateFlow<LastRead> = _lastRead

    private fun getLastRead(){
        viewModelScope.launch {
            repository.getLastRead().collect{
                _lastRead.value = it
            }
        }
    }

    fun setLastRead(lastRead: LastRead){
        repository.setLastRead(lastRead)
        getLastRead()
    }

    private var mediaPlayer = MediaPlayer()
    private var length = 0
    var isAudioStarted = mutableStateOf(false)
    var isAudioPlayed = mutableStateOf(false)

    fun playAudio(audio:String){
        // on below line we are setting audio stream type as
        // stream music on below line.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        // on below line we are running a try and catch block
        // for our media player.
        try {
            // on below line we are setting audio source
            // as audio url on below line.
            mediaPlayer.setDataSource(audio)

            // on below line we are preparing
            // our media player.
            mediaPlayer.prepare()

            // on below line we are starting
            // our media player.
            mediaPlayer.start()

        } catch (e: Exception) {

            // on below line we are
            // handling our exception.
            e.printStackTrace()
        }
    }

    fun resumeOrPauseAudio(){
        // if media player is playing.
        if (mediaPlayer.isPlaying) {
            // if media player is playing
            // we are stopping it on below line.
            mediaPlayer.pause()
            length = mediaPlayer.currentPosition
        } else{
            mediaPlayer.seekTo(length)
            mediaPlayer.start()
        }
    }

    fun stopAudio(){
        mediaPlayer.stop()
    }

    init {
        getLastRead()
    }



}