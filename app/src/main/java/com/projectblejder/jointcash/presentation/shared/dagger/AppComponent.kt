package com.projectblejder.jointcash.presentation.shared.dagger


import com.projectblejder.jointcash.presentation.shared.GlobalApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    AppModuleBinds::class,
    BasicFragmentsModule::class])
interface AppComponent : AndroidInjector<GlobalApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GlobalApplication>()
}
