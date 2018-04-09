package com.shivamsingh.blog_lite.ui.base

import android.os.Bundle
import butterknife.ButterKnife
import com.shivamsingh.blog_lite.platform.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        ButterKnife.bind(this)
        afterViews(savedInstanceState)
    }

    abstract fun layoutRes(): Int

    abstract fun afterViews(savedInstanceState: Bundle?)
}