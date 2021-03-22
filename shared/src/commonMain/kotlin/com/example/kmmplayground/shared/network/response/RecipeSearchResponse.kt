package com.example.kmmplayground.shared.network.response

import com.example.kmmplayground.shared.network.model.RecipeDto
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse(

    val count: Int,

    val results: List<RecipeDto>,
)