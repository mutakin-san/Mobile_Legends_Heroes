package com.mutakinxdicoding.mobilelegendsheroes.core.domain.repository

import com.mutakinxdicoding.mobilelegendsheroes.core.data.Resource
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.DetailHero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface IHeroRepository {
    fun getAllHero(): Flow<Resource<List<Hero>>>
    fun getDetailHero(heroId: Int): Flow<Resource<DetailHero>>
    fun getFavoriteHero(): Flow<List<Hero>>
    suspend fun setFavoriteHero(hero: Hero, state: Boolean)
}