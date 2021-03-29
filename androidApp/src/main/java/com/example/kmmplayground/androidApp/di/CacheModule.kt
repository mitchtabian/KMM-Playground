package com.example.kmmplayground.androidApp.di

import android.content.Context
import com.example.kmmplayground.androidApp.presentation.BaseApplication
import com.example.kmmplayground.cache.RecipeDatabase
import com.example.kmmplayground.shared.cache.DriverFactory
import com.example.kmmplayground.shared.cache.createDatabase
import com.example.kmmplayground.shared.cache.model.RecipeEntityMapper
import com.example.kmmplayground.shared.domain.util.DateUtil
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
        return createDatabase(driverFactory = DriverFactory(context))
    }
}








