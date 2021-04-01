package com.example.kmmplayground.shared.datasource.network

import com.example.kmmplayground.shared.datasource.network.model.RecipeDto
import com.example.kmmplayground.shared.datasource.network.model.RecipeSearchResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

//const val token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"

class RecipeServiceImpl: RecipeService {

    private val BASE_URL = "https://food2fork.ca/api/recipe"

    private val client: HttpClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true // if the server sends extra fields, ignore them
            })
        }
    }

    override suspend fun get(token: String, id: Int): RecipeDto {
        return client.get<RecipeDto>("$BASE_URL/get/?id=${id}"){
            headers {
                append("Authorization", token)
            }
        }
    }

    override suspend fun search(token: String, page: Int, query: String): RecipeSearchResponse {
        return client.get<RecipeSearchResponse>("$BASE_URL/search/?page=$page&query=$query"){
            headers {
                append("Authorization", token)
            }
        }
    }
}







