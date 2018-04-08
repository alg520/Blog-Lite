package com.aasaanjobs.partnerinternal.di.module

import android.content.Context
import android.util.Log
import com.aasaanjobs.partnerinternal.di.module.NetworkModule.AppInterceptor
import com.aasaanjobs.partnerinternal.di.module.NetworkModule.NetworkInterceptor
import com.aasaanjobs.partnerinternal.di.qualifiers.ForActivity
import com.aasaanjobs.partnerinternal.di.qualifiers.ForApplication
import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import dagger.multibindings.ElementsIntoSet
import java.util.*

@Module
class InstrumentationModule {

    @Provides
    @AppInterceptor
    @ElementsIntoSet
    @PerApplication
    fun networkInterceptor(): Set<Interceptor> {
        return Collections.emptySet<Interceptor>()
    }

    @Provides
    @AppInterceptor
    @ElementsIntoSet
    @PerApplication
    fun appInterceptor(): Set<Interceptor> {
        return Collections.emptySet<Interceptor>()
    }
}