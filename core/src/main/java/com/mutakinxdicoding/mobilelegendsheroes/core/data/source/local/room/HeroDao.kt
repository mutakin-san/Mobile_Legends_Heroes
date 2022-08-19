package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.room

import androidx.room.*
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Query("SELECT * FROM heroes")
    fun getAllHero(): Flow<List<HeroEntity>>

    @Query("SELECT * FROM heroes where isFavorite = 1")
    fun getFavoriteHero(): Flow<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(heroes: List<HeroEntity>)

    @Update
    suspend fun updateFavoriteHero(hero: HeroEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailHero(hero: DetailHeroEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSkill(skills: List<ItemSkillEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHeroSkillCrossRef(heroSkillCrossRef: List<HeroSkillCrossRef>)


    @Transaction
    @Query("SELECT * FROM detailHero WHERE heroId = :heroId")
    fun getDetailHeroWithSkill(heroId: Int): Flow<HeroWithSkill>
}
