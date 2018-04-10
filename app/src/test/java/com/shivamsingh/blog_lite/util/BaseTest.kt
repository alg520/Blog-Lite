package com.shivamsingh.blog_lite.util

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
abstract class BaseTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val overrideSchedulersRule = RxSchedulerOverrideRule()
}