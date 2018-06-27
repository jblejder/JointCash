package com.projectblejder.jointcash.domain.readModels

import android.content.Context
import com.projectblejder.jointcash.domain.Persons
import com.projectblejder.jointcash.domain.models.Person
import io.reactivex.Flowable
import javax.inject.Inject

class PersonsReadModel
@Inject constructor(
        private val context: Context,
        private val persons: Persons
) {

    fun observe(): Flowable<List<Person>> {
        return persons.observe()
    }
}
