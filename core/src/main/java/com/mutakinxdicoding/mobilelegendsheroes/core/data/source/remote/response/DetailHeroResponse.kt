package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailHeroResponse(
	@field:SerializedName("data")
	val heroDetail: HeroDetail,
)

data class HeroDetail(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("skill")
	val skill: Skill,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("cover_picture")
	val coverPicture: String,
)

data class SkillItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("tips")
	val tips: String
)

data class Skill(
	@field:SerializedName("skill")
	val skill: List<SkillItem>
)
