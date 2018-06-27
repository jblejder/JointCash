package com.projectblejder.jointcash.domain

import com.projectblejder.jointcash.domain.models.Person
import io.reactivex.Flowable

interface Persons {

    fun observe(): Flowable<List<Person>>
}
