package com.shivamsingh.blog_lite.ui.base

interface BaseView<in Presenter> {

    fun attachPresenter(presenter: Presenter)
}