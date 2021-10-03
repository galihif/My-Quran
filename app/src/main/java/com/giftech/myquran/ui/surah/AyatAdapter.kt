package com.giftech.myquran.ui.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftech.myquran.R
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.data.source.local.entity.LastReadAyatEntity
import com.giftech.myquran.databinding.ItemAyatBinding


class AyatAdapter: RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    private var listAyat = ArrayList<AyatEntity>()
    private lateinit var lastReadAyat: LastReadAyatEntity
    private lateinit var bookmarkCallback:BookmarkCallback

    fun setList(list: List<AyatEntity>){
        listAyat.clear()
        listAyat.addAll(list)
        notifyDataSetChanged()
    }

    fun setLastRead(lastReadAyatEntity: LastReadAyatEntity){
        this.lastReadAyat = lastReadAyatEntity
        notifyDataSetChanged()
    }

    fun onBookmarkClicked(bookmarkCallback: BookmarkCallback){
        this.bookmarkCallback = bookmarkCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val binding = ItemAyatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AyatViewHolder(binding,lastReadAyat)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val surah = listAyat[position]
        holder.bind(surah)
    }

    override fun getItemCount(): Int = listAyat.size

    inner class AyatViewHolder(
        private val binding: ItemAyatBinding,
        private val lastReadAyat:LastReadAyatEntity
        ): RecyclerView.ViewHolder(binding.root) {
        private var bookmarked = false

        fun bind(ayat: AyatEntity){

            bookmarked = isBookmarked(ayat)
            setBookmarkDrawable()

            with(binding){
                tvAyatNumber.text = ayat.nomor.toString()
                tvAyatArab.text = ayat.arab
                tvAyatArti.text = ayat.id

                btnBookmark.setOnClickListener {
                    bookmarked = !bookmarked
                    if(bookmarked){
                        bookmarkCallback.onResponseReceived(ayat)
                        lastReadAyat.nomorSurah = ayat.nomorSurah
                        lastReadAyat.nomorAyat = ayat.nomor
                        setBookmarkDrawable()
                    }
                }
            }
        }

        private fun isBookmarked(ayat: AyatEntity): Boolean {
            if(ayat.nomorSurah==lastReadAyat.nomorSurah && ayat.nomor==lastReadAyat.nomorAyat ){
                return true
            }
            return false
        }

        private fun setBookmarkDrawable() {
            if(bookmarked){
                binding.btnBookmark.setImageResource(R.drawable.ic_bookmark)
            }else{
                binding.btnBookmark.setImageResource(R.drawable.ic_bookmark_border)
            }
        }
    }

    interface BookmarkCallback{
        fun onResponseReceived(ayat:AyatEntity)
    }
}