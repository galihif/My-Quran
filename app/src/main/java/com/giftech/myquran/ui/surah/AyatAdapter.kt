package com.giftech.myquran.ui.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftech.myquran.data.source.local.entity.AyatEntity
import com.giftech.myquran.databinding.ItemAyatBinding


class AyatAdapter: RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    private var listAyat = ArrayList<AyatEntity>()

    fun setList(list: List<AyatEntity>){
        listAyat.clear()
        listAyat.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val binding = ItemAyatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AyatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val surah = listAyat[position]
        holder.bind(surah)
    }

    override fun getItemCount(): Int = listAyat.size

    class AyatViewHolder(private val binding: ItemAyatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(surah: AyatEntity){
            with(binding){

            }
        }
    }
}