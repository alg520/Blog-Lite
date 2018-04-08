package com.shivamsingh.blog_lite.di.module

import android.content.Context
import android.util.Log
import com.aasaanjobs.partnerinternal.di.qualifiers.ForApplication
import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.shivamsingh.blog_lite.di.modules.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
class InstrumentationModule {

    @Provides
    @NetworkModule.NetworkInterceptor
    @IntoSet
    @PerApplication
    fun loggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor({ message -> Log.d("OkHttp", message) })
                .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @NetworkModule.NetworkInterceptor
    @IntoSet
    @PerApplication
    fun stethoInterceptor(): Interceptor {
        return StethoInterceptor()
    }

    @Provides
    @NetworkModule.AppInterceptor
    @IntoSet
    @PerApplication
    fun chuckInterceptor(@ForApplication context: Context): Interceptor {
        return ChuckInterceptor(context)
    }
}