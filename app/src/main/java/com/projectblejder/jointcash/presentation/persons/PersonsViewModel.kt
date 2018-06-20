package com.projectblejder.jointcash.presentation.persons

import com.projectblejder.jointcash.infrastructure.AppDatabase
import com.projectblejder.jointcash.infrastructure.PersonsDao
import javax.inject.Inject


class PersonsViewModel
@Inject constructor(
        val database: AppDatabase
)
