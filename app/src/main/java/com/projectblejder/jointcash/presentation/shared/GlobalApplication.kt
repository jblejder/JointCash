package com.projectblejder.jointcash.presentation.shared

import com.projectblejder.jointcash.presentation.shared.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GlobalApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
