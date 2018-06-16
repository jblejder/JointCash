package com.projectblejder.jointcash.domain.models

data class Person(
        val id: Int?,
        var displayName: String
) {
    constructor(displayName: String) : this(null, displayName)
}
