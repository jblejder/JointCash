package com.projectblejder.jointcash.presentation.shared.dagger

import com.projectblejder.jointcash.presentation.persons.AddPersonFragment
import com.projectblejder.jointcash.presentation.persons.PersonsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BasicFragmentsModule {

    @ContributesAndroidInjector
    abstract fun personFragment(): PersonsFragment

    @ContributesAndroidInjector
    abstract fun addPersonFragment(): AddPersonFragment
}
