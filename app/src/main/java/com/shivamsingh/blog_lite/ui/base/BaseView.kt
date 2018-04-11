package com.shivamsingh.blog_lite.ui.base

interface BaseView<in Presenter> {

    fun attachPresenter(presenter: Presenter)

    fun showLoading()

    fun hideLoading()

    fun showError(message: String)
}