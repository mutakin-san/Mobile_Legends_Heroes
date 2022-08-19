package com.mutakinxdicoding.mobilelegendsheroes.di

import com.mutakinxdicoding.mobilelegendsheroes.core.domain.usecase.HeroInteractor
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.usecase.HeroUseCase
import com.mutakinxdicoding.mobilelegendsheroes.detail.DetailHeroViewModel
import com.mutakinxdicoding.mobilelegendsheroes.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<HeroUseCase> { HeroInteractor(get()) }
}


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailHeroViewModel(get()) }
}