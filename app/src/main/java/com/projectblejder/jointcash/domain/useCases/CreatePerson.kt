package com.projectblejder.jointcash.domain.useCases

import com.projectblejder.jointcash.infrastructure.PersonEntity
import com.projectblejder.jointcash.infrastructure.PersonsDao

class CreatePerson(
        val personsDao: PersonsDao
) {
    fun execute(name: String) {
        personsDao.create(PersonEntity(null, name))
    }
}

