package com.projectblejder.jointcash.presentation.persons

import com.projectblejder.jointcash.infrastructure.AppDatabase
import javax.inject.Inject


class PersonsViewModel
@Inject constructor(
        val database: AppDatabase
)
