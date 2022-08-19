package com.mutakinxdicoding.mobilelegendsheroes.core.domain.usecase

import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.repository.IHeroRepository

class HeroInteractor(private val heroRepository: IHeroRepository): HeroUseCase {
    override fun getAllHero() = heroRepository.getAllHero()

    override fun getDetailHero(
        heroId: Int,
    ) = heroRepository.getDetailHero(heroId)

    override fun getFavoriteHero() = heroRepository.getFavoriteHero()

    override suspend fun setFavoriteHero(hero: Hero, state: Boolean) = heroRepository.setFavoriteHero(hero, state)
}