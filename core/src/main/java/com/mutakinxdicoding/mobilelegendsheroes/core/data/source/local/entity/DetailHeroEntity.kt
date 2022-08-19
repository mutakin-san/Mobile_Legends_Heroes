package com.mutakinxdicoding.mobilelegendsheroes.core.data.source.local.entity

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "detailHero")
data class DetailHeroEntity(

    @PrimaryKey
    @NotNull
    val heroId: Int,

    val type: String,

    val name: String,

    val coverPicture: String,
)


@Entity(tableName = "skills")
data class ItemSkillEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val icon: String,

    val tips: String,
)

@Entity(primaryKeys = ["hId", "sId"])
data class HeroSkillCrossRef(
    val hId: Int,
    @ColumnInfo(index = true)
    val sId: Int,
)

data class HeroWithSkill(
    @Embedded
    val hero: DetailHeroEntity,

    @Relation(
        parentColumn = "heroId",
        entity = ItemSkillEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = HeroSkillCrossRef::class,
            parentColumn = "hId",
            entityColumn = "sId"
        )
    )
    val skills: List<ItemSkillEntity>

)