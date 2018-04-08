package com.shivamsingh.blog_lite.di.modules

import android.app.Application
import android.content.Context
import com.aasaanjobs.partnerinternal.di.qualifiers.ForApplication
import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class ApplicationModule() {

    @Provides
    @PerApplication
    @ForApplication
    fun providesApplicationContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    fun picasso(application: Application, client: OkHttpClient): Picasso {
        return Picasso.Builder(application.applicationContext)
                .downloader(OkHttp3Downloader(client))
                .build()
    }

}