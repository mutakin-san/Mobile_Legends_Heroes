package com.mutakinxdicoding.mobilelegendsheroes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutakinxdicoding.mobilelegendsheroes.core.data.Resource
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.DetailHero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.usecase.HeroUseCase
import kotlinx.coroutines.launch

class DetailHeroViewModel(private val heroUseCase: HeroUseCase) : ViewModel() {

    fun setFavoriteHero(hero: Hero, newStatus: Boolean) {
        viewModelScope.launch {
            heroUseCase.setFavoriteHero(hero, newStatus)
        }
    }

    fun getDetailHero(id: Int): LiveData<Resource<DetailHero>> {
        return heroUseCase.getDetailHero(id).asLiveData()
    }
}

