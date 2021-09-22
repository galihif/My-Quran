package com.giftech.myquran.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftech.myquran.data.source.local.entity.SurahEntity
import com.giftech.myquran.databinding.ItemSurahBinding
import com.giftech.myquran.ui.surah.SurahActivity

class SurahAdapter:RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    private var listSurah = ArrayList<SurahEntity>()

    fun setList(list: List<SurahEntity>){
        listSurah.clear()
        listSurah.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ItemSurahBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = listSurah[position]
        holder.bind(surah)
    }

    override fun getItemCount(): Int = listSurah.size

    class SurahViewHolder(private val binding: ItemSurahBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(surah:SurahEntity){
            with(binding){
                tvSurahNumber.text = surah.nomor.toString()
                tvSurahName.text = surah.nama
                tvSurahArab.text = surah.asma
                tvSurahTypeAyat.text ="${surah.type} - ${surah.ayat} AYAT"


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SurahActivity::class.java)
                    intent.putExtra(SurahActivity.EXTRA_SURAH, surah.nomor)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}