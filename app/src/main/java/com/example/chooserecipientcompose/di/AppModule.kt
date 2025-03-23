package com.example.chooserecipientcompose.di

import com.example.chooserecipientcompose.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.service
    }

    //        private const val BASE_URL = "http://10.0.2.2:8080" // Emulator localhost
//    private const val BASE_URL = "http://10.0.0.91:8080" // Physical device localhost
    /*
    In terminal:
    ifconfig | grep "inet "

    inet 127.0.0.1 netmask 0xff000000
    inet 10.0.0.91 netmask 0xffffff00 broadcast 10.0.0.255
     */
}