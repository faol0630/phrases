package com.example.phrases.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phrases.databinding.ItemAlbumBinding
import com.example.phrases.data.model.Phrase

class AdapterPhrase(
    private val itemClickListener: OnPhraseClickListener,
) : RecyclerView.Adapter<AdapterPhrase.ViewHolder>() {

    interface OnPhraseClickListener{
        fun onPhraseClick(phrase: Phrase, position: Int)
    }
    private var listPhrases : List<Phrase> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        val holder = ViewHolder(itemBinding.itemAlbumView)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != RecyclerView.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onPhraseClick(listPhrases[position], position)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.insideViewHolder(listPhrases[position])
    }

    override fun getItemCount() = listPhrases.size

    fun getData(phrases: List<Phrase>){
        this.listPhrases = phrases
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun insideViewHolder(phrase: Phrase){
            val itemVH = ItemAlbumBinding.bind(itemView)
            itemVH.apply {
                tvId.text = phrase.id.toString()
                tvTitle.text = phrase.title
            }
        }

    }

}