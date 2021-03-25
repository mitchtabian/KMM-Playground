package com.example.kmmplayground.shared.network

import com.example.kmmplayground.shared.network.model.RecipeDto
import com.example.kmmplayground.shared.network.model.RecipeSearchResponse
import io.ktor.utils.io.errors.*
import kotlin.coroutines.cancellation.CancellationException

interface RecipeService {

    @Throws(Exception::class)
    suspend fun get(token: String, id: Int): RecipeDto

    @Throws(Exception::class)
    suspend fun search(token: String, page: Int, query: String): RecipeSearchResponse
}