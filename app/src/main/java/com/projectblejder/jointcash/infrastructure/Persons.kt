package com.projectblejder.jointcash.infrastructure

import android.content.res.Resources
import com.projectblejder.jointcash.R
import com.projectblejder.jointcash.domain.Persons
import com.projectblejder.jointcash.infrastructure.dao.PersonsDao
import io.reactivex.Flowable
import javax.inject.Inject

private typealias DomainPerson = com.projectblejder.jointcash.domain.models.Person
private typealias InfraPerson = com.projectblejder.jointcash.infrastructure.models.Person

class Persons
@Inject constructor(
        private val resources: Resources,
        private val personsDao: PersonsDao
) : Persons {

    override fun observe(): Flowable<List<DomainPerson>> {
        return personsDao.allRx()
                .map(::map)
    }

    private fun map(persons: List<InfraPerson>): List<DomainPerson> {
        return persons.map(::mapPerson)
    }

    private fun mapPerson(person: InfraPerson): DomainPerson {
        return DomainPerson(id = person.id, displayName = tryKeyword(person.displayName))
    }

    private fun tryKeyword(value: String) = when (value) {
        "{you}" -> resources.getString(R.string.you)
        else -> value
    }
}
