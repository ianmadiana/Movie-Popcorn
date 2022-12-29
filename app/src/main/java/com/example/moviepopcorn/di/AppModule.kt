package com.example.moviepopcorn.di

import android.content.Context
import androidx.room.Room
import com.example.moviepopcorn.api.MovieApi
import com.example.moviepopcorn.data.local.FavoriteMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//modul untuk injeksi class agar mengurangi ketergantungan antar class
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    //inject moviDB dan DAO
    @Singleton
    @Provides
    fun provideFavMovieDatabase(
        @ApplicationContext app:Context
    ) = Room.databaseBuilder(
        app,
        FavoriteMovieDatabase::class.java,
        "movie_db"
    ).build()

    @Singleton
    @Provides
    fun provideFavMovieDao(db: FavoriteMovieDatabase) = db.getFavoriteMovieDao()

//  retrofit object
//  JSON diparsing menggunakan GSON
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//  inject class MovieApi
    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}