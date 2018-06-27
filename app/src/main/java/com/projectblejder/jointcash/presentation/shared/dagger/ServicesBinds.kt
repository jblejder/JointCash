package com.projectblejder.jointcash.presentation.shared.dagger

import com.projectblejder.jointcash.domain.Persons
import dagger.Binds
import dagger.Module

@Module
interface ServicesBinds {

    @Binds
    fun bind(arg: com.projectblejder.jointcash.infrastructure.Persons): Persons
}
