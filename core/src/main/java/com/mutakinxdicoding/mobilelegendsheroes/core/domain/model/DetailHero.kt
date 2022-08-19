package com.mutakinxdicoding.mobilelegendsheroes.core.domain.model

data class DetailHero(

    val type: String? = null,

    val skills: List<ItemSkill> = emptyList(),

    val name: String? = null,

    val coverPicture: String? = null,
)

data class ItemSkill(
    val name: String,

    val icon: String,

    val tips: String
)