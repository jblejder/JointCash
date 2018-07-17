package com.projectblejder.jointcash.domain.models

typealias GroupId = Long

data class Group(
        val id: GroupId?,
        val name: String,
        val participants: List<PersonId> = emptyList()
) {
    constructor(name: String) : this(null, name)
}
