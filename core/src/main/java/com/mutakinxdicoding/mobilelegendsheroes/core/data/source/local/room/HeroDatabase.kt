package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.DetailHeroEntity
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.HeroEntity
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.HeroSkillCrossRef
import com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity.ItemSkillEntity

@Database(entities = [HeroEntity::class, DetailHeroEntity::class, ItemSkillEntity::class, HeroSkillCrossRef::class], version = 1, exportSchema = false)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
}