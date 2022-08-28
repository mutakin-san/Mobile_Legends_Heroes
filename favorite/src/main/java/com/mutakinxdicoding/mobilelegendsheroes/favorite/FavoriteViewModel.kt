package com.mutakinxdicoding.mobilelegendsheroes.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.usecase.HeroUseCase

class FavoriteViewModel(heroUseCase: HeroUseCase) : ViewModel() {

    val favoriteHero = heroUseCase.getFavoriteHero().asLiveData()

}

