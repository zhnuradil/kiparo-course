/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappdagger.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.kiparo.newsappdagger.di.DaggerAppComponent
import com.kiparo.newsappdagger.di.DiProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        setupDagger()
    }

    private fun setupDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .addContext(this)
            .build()
        DiProvider.appComponent = appComponent
    }
}