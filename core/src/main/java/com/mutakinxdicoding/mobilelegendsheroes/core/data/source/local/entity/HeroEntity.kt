package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
data class HeroEntity(
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "heroId")
	val heroId: Int,
	@ColumnInfo(name = "heroName")
	val heroName: String,
	@ColumnInfo(name = "heroAvatar")
	val heroAvatar: String,
	@ColumnInfo(name = "isFavorite")
	var isFavorite: Boolean = false
)