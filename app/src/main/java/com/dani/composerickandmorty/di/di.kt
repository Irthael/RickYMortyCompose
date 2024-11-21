package com.dani.composerickandmorty.di

import android.app.Application
import androidx.room.Room
import com.dani.composerickandmorty.model.APIService
import com.dani.composerickandmorty.model.database.ApiCharactersDatabase
import com.dani.composerickandmorty.model.mycharacters.RetrofitDataSource
import com.dani.composerickandmorty.model.mycharacters.RoomDataSource
import com.dani.data.source.LocalDataSource
import com.dani.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @ApiEndpoint
    fun provideApiEndpoint(): String = "https://rickandmortyapi.com/api/"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideRestAdapter(@ApiEndpoint apiEndPoint: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideCharactersService(restAdapter: Retrofit): APIService = restAdapter.create()

    @Provides
    fun localDataSourceProvider(db: ApiCharactersDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(rickmortyServiceManager: APIService): RemoteDataSource
            = RetrofitDataSource(rickmortyServiceManager)

    @Provides
    @Singleton
    fun databaseProvider(app: Application): ApiCharactersDatabase = Room.databaseBuilder(
        app,
        ApiCharactersDatabase::class.java,
        "api_db"
    ).build()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint