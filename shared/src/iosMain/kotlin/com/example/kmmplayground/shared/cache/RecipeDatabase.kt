package com.example.kmmplayground.shared.cache

import com.example.kmmplayground.cache.RecipeDatabase
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(RecipeDatabase.Schema, "recipes.db")
    }
}