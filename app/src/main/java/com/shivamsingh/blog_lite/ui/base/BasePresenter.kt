package com.shivamsingh.blog_lite.ui.base

interface BasePresenter<in View> {

    fun takeView(view: View)
}