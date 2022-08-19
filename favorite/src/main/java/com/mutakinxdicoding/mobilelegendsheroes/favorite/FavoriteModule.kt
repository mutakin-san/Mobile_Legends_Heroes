package com.mutakinxdicoding.mobilelegendsheroes.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}