package com.example.kmmplayground.androidApp.di

import com.example.kmmplayground.androidApp.presentation.BaseApplication
import com.example.kmmplayground.cache.RecipeDatabase
import com.example.kmmplayground.shared.datasource.cache.DriverFactory
import com.example.kmmplayground.shared.datasource.cache.RecipeDatabaseFactory
import com.example.kmmplayground.shared.datasource.cache.model.RecipeEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeEntityMapper(): RecipeEntityMapper{
        return RecipeEntityMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }
}








