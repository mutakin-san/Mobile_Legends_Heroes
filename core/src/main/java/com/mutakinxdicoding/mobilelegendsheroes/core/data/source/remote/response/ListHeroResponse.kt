package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListHeroResponse(
	@field:SerializedName("data")
	val data: List<HeroItem>,
)

data class HeroItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("heroid")
	val heroId: Int,

	@field:SerializedName("key")
	val key: String
)
