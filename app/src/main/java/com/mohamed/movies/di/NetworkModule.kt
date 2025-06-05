package com.mohamed.movies.di

import com.mohamed.movies.BuildConfig
import com.mohamed.movies.data.retrofit.MovieAPIInterface
import com.mohamed.movies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okhttp = OkHttpClient.Builder()
            .connectTimeout(Constants.Network.CONNECT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(Constants.Network.READ_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(Constants.Network.WRITE_TIMEOUT, TimeUnit.MINUTES)
        okhttp.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
            val request = requestBuilder.build()
            chain.proceed(request)
        })
        if (BuildConfig.DEBUG)
            okhttp.addInterceptor(httpLoggingInterceptor)
        return okhttp.build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL.BASE_NETWORK_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiInterface(retrofit: Retrofit): MovieAPIInterface {
        return retrofit.create(MovieAPIInterface::class.java)
    }


}