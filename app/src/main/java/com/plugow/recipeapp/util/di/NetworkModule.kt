package com.plugow.recipeapp.util.di

import com.plugow.recipeapp.BuildConfig
import com.plugow.recipeapp.util.api.RecipeApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRecipeApi(okHttpClient: OkHttpClient, moshi: Moshi): RecipeApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RecipeApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(token: String): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val ongoing = chain.request().newBuilder()
                ongoing.addHeader("Authorization", token)
                return@addInterceptor chain.proceed(ongoing.build())
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideSerialization(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    fun provideAuthToken(): String{
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

}