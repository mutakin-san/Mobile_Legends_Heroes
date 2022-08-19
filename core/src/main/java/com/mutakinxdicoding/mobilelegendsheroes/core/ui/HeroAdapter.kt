package com.mutakinxdicoding.mobilelegendsheroes.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mutakinxdicoding.mobilelegendsheroes.core.R
import com.mutakinxdicoding.mobilelegendsheroes.core.databinding.ItemListHeroBinding
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero

class HeroAdapter(private val onItemClick: ((Hero) -> Unit)) : ListAdapter<Hero, HeroAdapter.ListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val holder = ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_hero, parent, false))
        holder.itemView.setOnClickListener {
            onItemClick(getItem(holder.adapterPosition))
        }

        return holder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListHeroBinding.bind(itemView)
        fun bind(data: Hero) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https:${data.heroAvatar}")
                    .into(ivItemImage)
                tvItemTitle.text = data.heroName
            }
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Hero>(){
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.heroId == newItem.heroId
            }
        }
    }
}