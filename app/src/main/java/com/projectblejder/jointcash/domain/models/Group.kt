package com.projectblejder.jointcash.domain.models

data class Group(
        val id: Int?,
        val name: String
) {
    constructor(name: String) : this(null, name)
}
