package com.shivamsingh.blog_lite.util

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.quality.Strictness

@RunWith(MockitoJUnitRunner::class)
abstract class BaseTest {

    @JvmField
    @Rule
    val mockitoRule = MockitoJUnit.rule().strictness(Strictness.WARN)

    @JvmField
    @Rule
    val overrideSchedulersRule = RxSchedulerOverrideRule()
}