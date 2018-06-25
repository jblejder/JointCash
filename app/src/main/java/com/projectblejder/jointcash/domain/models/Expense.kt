package com.projectblejder.jointcash.domain.models

data class Expense(
        val name: String,
        val group: Group,
        val payedBy: Pair<Person, Double>,
        val usedBy: Pair<Person, Double>
)
