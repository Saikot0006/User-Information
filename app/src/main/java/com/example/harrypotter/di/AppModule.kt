package com.example.harrypotter.di


import com.example.harrypotter.api.ApiService
import com.example.harrypotter.api.RetrofitUtils.retrofitInstance
import com.example.harrypotter.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("apiBaseUrl")
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(@Named("apiBaseUrl") baseUrl : String,okHttpClient: OkHttpClient) : ApiService =
        retrofitInstance(baseUrl,okHttpClient).create(ApiService::class.java)

}