package com.example.kmmplayground.shared.datasource.network

import com.example.kmmplayground.shared.datasource.network.model.RecipeDto
import com.example.kmmplayground.shared.datasource.network.model.RecipeSearchResponse

interface RecipeService {

    suspend fun get(token: String, id: Int): RecipeDto

    suspend fun search(token: String, page: Int, query: String): RecipeSearchResponse
}