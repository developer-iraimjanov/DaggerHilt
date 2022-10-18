package com.example.hiltcontact.di

import com.example.hiltcontact.data.remote.MyApi
import com.example.hiltcontact.data.repository.MyRepository
import com.example.hiltcontact.data.repository.MyRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        return Retrofit.Builder()
            .baseUrl("https://usersiqboljon.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepositoryImp(api: MyApi): MyRepository {
        return MyRepositoryImp(api)
    }

}
