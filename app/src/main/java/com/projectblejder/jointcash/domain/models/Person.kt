package com.projectblejder.jointcash.domain.models

typealias PersonId = Long

data class Person(
        val id: PersonId?,
        var displayName: String
) {
    constructor(displayName: String) : this(null, displayName)
}
