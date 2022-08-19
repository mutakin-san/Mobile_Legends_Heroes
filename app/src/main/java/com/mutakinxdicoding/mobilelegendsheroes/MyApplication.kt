package com.mutakinxdicoding.mobilelegendsheroes

import android.app.Application
import com.mutakinxdicoding.mobilelegendsheroes.core.di.databaseModule
import com.mutakinxdicoding.mobilelegendsheroes.core.di.networkModule
import com.mutakinxdicoding.mobilelegendsheroes.core.di.repositoryModule
import com.mutakinxdicoding.mobilelegendsheroes.di.useCaseModule
import com.mutakinxdicoding.mobilelegendsheroes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}