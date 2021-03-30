package com.example.kmmplayground.shared.network.model

import com.example.kmmplayground.shared.domain.model.Recipe
import com.example.kmmplayground.shared.domain.util.DateUtil
import com.example.kmmplayground.shared.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {

    private val dateUtil = DateUtil()

    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = model.ingredients,
            dateAdded = dateUtil.toLocalDate(model.longDateAdded.toDouble()),
            dateUpdated = dateUtil.toLocalDate(model.longDateUpdated.toDouble()),
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = domainModel.ingredients,
            longDateAdded = dateUtil.toEpochSeconds(domainModel.dateAdded).toLong(),
            longDateUpdated = dateUtil.toEpochSeconds(domainModel.dateUpdated).toLong()
        )
    }

    fun toDomainList(initial: List<RecipeDto>): List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Recipe>): List<RecipeDto>{
        return initial.map { mapFromDomainModel(it) }
    }


}