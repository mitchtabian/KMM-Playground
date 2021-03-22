package com.example.kmmplayground.shared.network.model

import com.example.kmmplayground.shared.domain.model.Recipe
import com.example.kmmplayground.shared.domain.util.DomainMapper
import kotlinx.datetime.*

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {

    override fun mapToDomainModel(model: RecipeDto): Recipe {
        val currentMoment: Instant = Clock.System.now()
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = model.ingredients,
            dateAdded = currentMoment.toLocalDateTime(TimeZone.UTC).date, // TODO("fix this later")
            dateUpdated = currentMoment.toLocalDateTime(TimeZone.UTC).date, // TODO("fix this later")
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        val currentMoment: Instant = Clock.System.now()
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = domainModel.ingredients,
            longDateAdded = currentMoment.toEpochMilliseconds(), // TODO("fix this later")
            longDateUpdated = currentMoment.toEpochMilliseconds(), // TODO("fix this later")
        )
    }

    fun toDomainList(initial: List<RecipeDto>): List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Recipe>): List<RecipeDto>{
        return initial.map { mapFromDomainModel(it) }
    }


}