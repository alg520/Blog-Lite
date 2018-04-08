package com.shivamsingh.blog_lite.ui.base

import android.os.Bundle
import butterknife.ButterKnife
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        ButterKnife.bind(this)
        afterViews(savedInstanceState)
    }

    abstract fun layoutRes(): Int

    abstract fun afterViews(savedInstanceState: Bundle?)
}