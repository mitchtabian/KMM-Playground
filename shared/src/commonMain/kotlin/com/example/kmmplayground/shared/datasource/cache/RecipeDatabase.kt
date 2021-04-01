package com.example.kmmplayground.shared.datasource.cache

import com.example.kmmplayground.cache.RecipeDatabase
import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
){
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
   fun createDriver(): SqlDriver
}

