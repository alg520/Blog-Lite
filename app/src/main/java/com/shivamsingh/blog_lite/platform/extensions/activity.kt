package com.shivamsingh.blog_lite.platform.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.setScreen(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment, fragment.javaClass.simpleName)
            .commit()
}