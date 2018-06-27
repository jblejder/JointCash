package com.projectblejder.jointcash.presentation.persons

import com.projectblejder.jointcash.domain.Persons
import javax.inject.Inject


class PersonsViewModel
@Inject constructor(
        val persons: Persons
) {
    val personsFeed = persons.observe()
}
