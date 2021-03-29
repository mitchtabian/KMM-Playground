package com.example.kmmplayground.androidApp.di

import android.content.Context
import com.example.kmmplayground.androidApp.presentation.BaseApplication
import com.example.kmmplayground.shared.domain.util.DateUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideDateUtil(): DateUtil {
        return DateUtil()
    }
}











