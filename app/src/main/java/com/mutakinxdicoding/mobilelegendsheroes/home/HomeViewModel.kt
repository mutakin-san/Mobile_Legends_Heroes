package com.mutakinxdicoding.mobilelegendsheroes.home

import androidx.lifecycle.ViewModel
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.usecase.HeroUseCase

class HomeViewModel(heroUseCase: HeroUseCase) : ViewModel() {

    val heroes = heroUseCase.getAllHero().asLiveData()

}

