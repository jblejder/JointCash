package com.projectblejder.jointcash.presentation.shared.dagger

import com.projectblejder.jointcash.domain.Groups
import com.projectblejder.jointcash.domain.Persons
import dagger.Binds
import dagger.Module

@Module
interface ServicesBinds {

    @Binds
    fun persons(arg: com.projectblejder.jointcash.infrastructure.Persons): Persons

    @Binds
    fun groups(arg: com.projectblejder.jointcash.infrastructure.Groups): Groups
}
