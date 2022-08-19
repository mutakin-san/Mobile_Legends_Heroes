package com.mutakinxdicoding.mobilelegendsheroes.core.di

import androidx.room.Room
import com.mutakinxdicoding.mobilelegendsheroes.core.BuildConfig
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.LocalDataSource
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.room.HeroDatabase
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.RemoteDataSource
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.network.HeroService
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.repository.IHeroRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<HeroDatabase>().heroDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            HeroDatabase::class.java,
            "Hero.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}


val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val baseUrl = "https://unofficial-mobile-legends-api.vercel.app/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(HeroService::class.java)
    }
}



val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IHeroRepository> {
        com.mutakinxdicoding.mobilelegendsheroes.core.data.HeroRepository(
            get(),
            get()
        )
    }
}