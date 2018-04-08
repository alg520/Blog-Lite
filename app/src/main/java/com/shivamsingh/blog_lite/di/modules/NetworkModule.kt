package com.shivamsingh.blog_lite.di.modules

import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shivamsingh.blog_lite.BuildConfig
import com.shivamsingh.blog_lite.di.module.InstrumentationModule
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier

@Module(includes = [(InstrumentationModule::class), (RxModule::class)])
class NetworkModule {
    companion object {
        const val API_BASE_URL = "API_BASE_URL"
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AppInterceptor

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NetworkInterceptor

    @Provides
    @Named(API_BASE_URL)
    @PerApplication
    fun providesAPIBaseUrl(): String {
        return BuildConfig.API_ENDPOINT
    }

    @Provides
    @PerApplication
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @PerApplication
    fun providesAPIOkHttpClient(@AppInterceptor appInterceptors: Set<@JvmSuppressWildcards Interceptor>,
                                @NetworkInterceptor networkInterceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.interceptors().addAll(appInterceptors)
        client.networkInterceptors().addAll(networkInterceptors)
        return client.build()
    }

    @Provides
    @PerApplication
    fun providesRetrofit(@Named(API_BASE_URL) apiUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(apiUrl)
                .client(client)
                .build()
    }
}