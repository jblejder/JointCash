package com.projectblejder.jointcash.domain.useCases

import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.infrastructure.dao.PersonsDao

class CreatePerson(
        val personsDao: PersonsDao
) {
    fun execute(name: String) {
        personsDao.create(Person(null, name))
    }
}

