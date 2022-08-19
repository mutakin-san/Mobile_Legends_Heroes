package com.mutakinxdicoding.mobilelegendsheroes.core.utils

import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.DetailHeroEntity
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.HeroEntity
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.HeroWithSkill
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.HeroDetail
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.remote.response.HeroItem
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.DetailHero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.Hero
import com.mutakinxdicoding.mobilelegendsheroes.core.domain.model.ItemSkill


object DataMapper {
    fun mapResponsesToEntities(input: List<HeroItem>): List<HeroEntity> {
        val heroList = ArrayList<HeroEntity>()
        input.map {
            val hero = HeroEntity(
                heroId = it.heroId,
                heroName = it.name,
                heroAvatar = it.key,
                isFavorite = false
            )
            heroList.add(hero)
        }
        return heroList
    }

    fun mapEntitiesToDomain(entities: List<HeroEntity>): List<Hero> {
        return entities.map {
            Hero(
                heroId = it.heroId,
                heroName = it.heroName,
                heroAvatar = it.heroAvatar,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(hero: Hero): HeroEntity {
        return HeroEntity(
            heroId = hero.heroId,
            heroName = hero.heroName,
            heroAvatar = hero.heroAvatar,
            isFavorite = hero.isFavorite
        )
    }

    fun mapEntityToDomain(entity: HeroWithSkill?): DetailHero {
        entity?.let {

            val skills = entity.skills.map {
                ItemSkill(it.name, it.icon, it.tips)
            }

            return DetailHero(
                name = entity.hero.name,
                type = entity.hero.type,
                coverPicture = entity.hero.coverPicture,
                skills = skills,
            )
        }

        return DetailHero()
    }

    fun mapResponseToEntity(heroId: Int, data: HeroDetail): DetailHeroEntity {
        return DetailHeroEntity(
            heroId = heroId,
            name = data.name,
            type = data.type,
            coverPicture = data.coverPicture
        )
    }

}