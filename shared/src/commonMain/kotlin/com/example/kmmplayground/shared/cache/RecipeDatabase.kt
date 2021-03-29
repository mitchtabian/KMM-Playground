package com.example.kmmplayground.shared.cache

import com.example.kmmplayground.cache.RecipeDatabase
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): RecipeDatabase {
    return RecipeDatabase(driverFactory.createDriver())
}