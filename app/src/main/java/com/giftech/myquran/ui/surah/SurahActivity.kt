package com.giftech.myquran.ui.surah

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.myquran.R
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.databinding.ActivitySurahBinding
import com.giftech.myquran.viewmodel.ViewModelFactory
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer


class SurahActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_SURAH = "1"
    }

    private lateinit var binding:ActivitySurahBinding
    private lateinit var player: SimpleExoPlayer
    private var isPlayed = false
    private var isPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this,factory)[SurahViewModel::class.java]
        val adapter = AyatAdapter()

        val extras = intent.extras
        if(extras!=null){
            val surah = extras.getParcelable<SurahEntity>(EXTRA_SURAH)

            Log.d("galih", surah.toString())
            populateView(surah!!)

            setLoading(true)
            viewmodel.getAyatByNomorSurah(surah.nomor).observe(this,{ res ->
                adapter.setList(res)
                setLoading(false)
            })

            viewmodel.getLastRead().observe(this, {lastRead ->
                adapter.setLastRead(lastRead)
            })

            adapter.onBookmarkClicked(object : AyatAdapter.BookmarkCallback{
                override fun onResponseReceived(ayat: AyatEntity) {
                    val text = "Surah ${surah.nama} Ayat ${ayat.nomor} saved to last read"
                    Toast.makeText(this@SurahActivity, text, Toast.LENGTH_SHORT).show()
                    viewmodel.setLastRead(LastReadAyatEntity(ayat.nomor,ayat.nomorSurah,surah.nama))
                }
            })

            with(binding.rvAyat){
                this.layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun populateView(surah:SurahEntity){
        with(binding.surahDetail){
            tvSurahName.text = surah.nama
            tvSurahArti.text = surah.arti
            tvSurahTypeAyat.text = "${surah.type} - ${surah.ayat} AYAT"

            player = SimpleExoPlayer.Builder(this@SurahActivity).build()

            btnAudio.setOnClickListener {
                if(isPlayed){
                    pausePlayer()
                    btnAudio.setImageResource(R.drawable.ic_play)
                }else{
                    if(isPaused){
                        resumePlayer()
                    }else{
                        playAudio(surah.audio)
                    }
                    btnAudio.setImageResource(R.drawable.ic_pause)
                }
            }

            player.addListener(object : Player.EventListener {
                override fun onPlaybackStateChanged(state: Int) {
                    if (state == Player.STATE_ENDED) {
                        btnAudio.setImageResource(R.drawable.ic_play)
                    }
                }
            })
        }

        with(binding){
            tvSurahName.text = surah.nama
            tvSurahName.setOnClickListener {
                onBackPressed()
                player.stop()
            }
        }
    }

    private fun playAudio(audio: String) {
        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(audio))
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Start the playback.
        player.play()

        isPlayed = true

        Toast.makeText(this@SurahActivity, "Playing", Toast.LENGTH_LONG).show()
    }

    private fun pausePlayer() {
        isPlayed = false
        isPaused = true

        player.playWhenReady = false
        player.playbackState
    }

    private fun resumePlayer() {
        isPaused = false
        isPlayed = true

        player.playWhenReady = true
        player.playbackState
    }

    private fun setLoading(isLoading:Boolean){
        if(isLoading){
            binding.rvAyat.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        } else{
            binding.loading.visibility = View.GONE
            binding.rvAyat.visibility = View.VISIBLE
        }
    }
}