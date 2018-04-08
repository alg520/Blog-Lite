package com.shivamsingh.blog_lite.di.modules

import android.app.Application
import android.content.Context
import com.aasaanjobs.partnerinternal.di.qualifiers.ForApplication
import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule() {

    @Provides
    @PerApplication
    @ForApplication
    fun providesApplicationContext(application: Application): Context {
        return application
    }

}