package com.mutakinxdicoding.mobilelegendsheroes.core.data

import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.LocalDataSource
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.HeroSkillCrossRef
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.ItemSkillEntity
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.RemoteDataSource
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.network.ApiResponse
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.HeroDetail
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.HeroItem
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.DetailHero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.repository.IHeroRepository
import com.mutakinxdicoding.mobilelegendsheroes.core.utils.DataMapper
import kotlinx.coroutines.flow.*

class HeroRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IHeroRepository{

    override fun getAllHero(): Flow<Resource<List<Hero>>> =
        object : com.mutakinxdicoding.mobilelegendsheroes.core.data.NetworkBoundResource<List<Hero>, List<HeroItem>>() {
            override fun loadFromDB(): Flow<List<Hero>> {
                return localDataSource.getAllHero().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Hero>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<HeroItem>>> {
                return remoteDataSource.getAllHero()
            }

            override suspend fun saveCallResult(data: List<HeroItem>) {
                val heroList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertHero(heroList)
            }
        }.asFlow()

    override fun getDetailHero(heroId: Int): Flow<Resource<DetailHero>> =
        object : com.mutakinxdicoding.mobilelegendsheroes.core.data.NetworkBoundResource<DetailHero, HeroDetail>() {
            override fun loadFromDB(): Flow<DetailHero> {
                return localDataSource.getDetailHeroWithSkill(heroId).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: DetailHero?): Boolean {
                return data == null || data.name.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<HeroDetail>> {
               return remoteDataSource.getDetailHero(heroId)
            }

            override suspend fun saveCallResult(data: HeroDetail) {
                val detailHero = DataMapper.mapResponseToEntity(heroId, data)
                localDataSource.insertDetailHero(detailHero)
                val skills = data.skill.skill.map {
                    ItemSkillEntity(name = it.name, icon = it.icon, tips = it.tips)
                }
                val skillIds = localDataSource.insertSkills(skills)
                if(skillIds.isNotEmpty()) {
                    val listHeroSkillCrossRef = skillIds.map {
                        HeroSkillCrossRef(heroId, it)
                    }

                    localDataSource.insertHeroSkillCrossRef(listHeroSkillCrossRef)
                }
            }
        }.asFlow()

    override fun getFavoriteHero(): Flow<List<Hero>> {
        return localDataSource.getFavoriteHero().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteHero(hero: Hero, state: Boolean) {
        val heroEntity = DataMapper.mapDomainToEntity(hero)
        localDataSource.setFavoriteHero(heroEntity, state)
    }
}

