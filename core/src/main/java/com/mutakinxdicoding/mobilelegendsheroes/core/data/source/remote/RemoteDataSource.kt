package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote

import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.network.ApiResponse
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.network.HeroService
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.HeroDetail
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.HeroItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val heroService: HeroService) {

    suspend fun getAllHero(): Flow<ApiResponse<List<HeroItem>>> {

        return flow {
            try {
                val dataArray = heroService.getAllHero()
                if (dataArray.data.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage?.toString() ?: e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getDetailHero(id: Int): Flow<ApiResponse<HeroDetail>> {
        return flow {
            try {
                val data = heroService.getDetailHero(id)
                emit(ApiResponse.Success(data.heroDetail))
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage?.toString() ?: e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}

