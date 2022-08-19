package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local

import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.*
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.room.HeroDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val heroDao: HeroDao) {

    fun getAllHero(): Flow<List<HeroEntity>> = heroDao.getAllHero()

    fun getFavoriteHero(): Flow<List<HeroEntity>> = heroDao.getFavoriteHero()

    suspend fun insertHero(heroList: List<HeroEntity>) = heroDao.insertHero(heroList)

    suspend fun setFavoriteHero(hero: HeroEntity, newState: Boolean) {
        hero.isFavorite = newState
        heroDao.updateFavoriteHero(hero)
    }


    suspend fun insertDetailHero(hero: DetailHeroEntity) {
        heroDao.insertDetailHero(hero)
    }

    suspend fun insertSkills(skills: List<ItemSkillEntity>): List<Int> {
        return heroDao.insertSkill(skills).map { it.toInt() }
    }

    suspend fun insertHeroSkillCrossRef(heroSkillCrossRef: List<HeroSkillCrossRef>) {
        heroDao.insertHeroSkillCrossRef(heroSkillCrossRef)
    }


    fun getDetailHeroWithSkill(heroId: Int): Flow<HeroWithSkill>{
        return heroDao.getDetailHeroWithSkill(heroId)
    }
}