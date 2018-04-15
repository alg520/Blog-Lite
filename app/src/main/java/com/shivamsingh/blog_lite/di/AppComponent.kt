package com.shivamsingh.blog_lite.di

import android.app.Application
import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import com.shivamsingh.blog_lite.BlogApplication
import com.shivamsingh.blog_lite.di.modules.ActivityBindingModule
import com.shivamsingh.blog_lite.di.modules.ApplicationModule
import com.shivamsingh.blog_lite.di.modules.NetworkModule
import com.shivamsingh.blog_lite.di.modules.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@PerApplication
@Component(modules = [
    ActivityBindingModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    RoomModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BlogApplication)

    override fun inject(instance: DaggerApplication)
}
