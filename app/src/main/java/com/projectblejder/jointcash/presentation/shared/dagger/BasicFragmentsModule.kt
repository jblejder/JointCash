package com.projectblejder.jointcash.presentation.shared.dagger

import com.projectblejder.jointcash.presentation.expenses.ExpensesFragment
import com.projectblejder.jointcash.presentation.expenses.GroupsDialogFragment
import com.projectblejder.jointcash.presentation.persons.AddPersonDialogFragment
import com.projectblejder.jointcash.presentation.persons.AddPersonFragment
import com.projectblejder.jointcash.presentation.persons.PersonsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BasicFragmentsModule {

    @ContributesAndroidInjector
    abstract fun personsFragment(): PersonsFragment

    @ContributesAndroidInjector
    abstract fun addPersonFragment(): AddPersonFragment

    @ContributesAndroidInjector
    abstract fun addPersonDialogFragment(): AddPersonDialogFragment

    @ContributesAndroidInjector
    abstract fun groupsDialogFragment(): GroupsDialogFragment

    @ContributesAndroidInjector
    abstract fun expensesFragment(): ExpensesFragment
}
