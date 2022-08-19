package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.network

import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.DetailHeroResponse
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.ListHeroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {
    @GET("/api/heroes")
    suspend fun getAllHero(): ListHeroResponse

    @GET("/api/heroes/{id}")
    suspend fun getDetailHero(@Path("id") id: Int): DetailHeroResponse

}