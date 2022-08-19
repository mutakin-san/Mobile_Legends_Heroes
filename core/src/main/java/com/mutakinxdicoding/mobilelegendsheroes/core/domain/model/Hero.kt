package com.mutakinxdicoding.mobilelegendsheroes.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero (
    val heroId: Int,
    val heroName: String,
    val heroAvatar: String,
    val isFavorite: Boolean
) : Parcelable