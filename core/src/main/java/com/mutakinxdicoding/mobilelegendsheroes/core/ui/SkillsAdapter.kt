package com.mutakinxdicoding.mobilelegendsheroes.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mutakinxdicoding.mobilelegendsheroes.core.R
import com.mutakinxdicoding.mobilelegendsheroes.core.databinding.ItemSkillLayoutBinding
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.ItemSkill

class SkillsAdapter : ListAdapter<ItemSkill, SkillsAdapter.ListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_skill_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSkillLayoutBinding.bind(itemView)
        fun bind(data: ItemSkill) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https:${data.icon}")
                    .into(ivSkillIcon)
                tvSkillName.text = data.name
                tvSkillTips.text = data.tips
            }
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ItemSkill>(){
            override fun areItemsTheSame(oldItem: ItemSkill, newItem: ItemSkill): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemSkill, newItem: ItemSkill): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}