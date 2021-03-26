package com.example.kmmplayground.shared.cache

import com.example.kmmplayground.cache.RecipeDatabase
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    expect fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory): RecipeDatabase {
    val driver = driverFactory.createDriver()
    val database = RecipeDatabase(driver)
}