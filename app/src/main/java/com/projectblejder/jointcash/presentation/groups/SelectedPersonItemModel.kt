package com.projectblejder.jointcash.presentation.groups

import com.projectblejder.jointcash.domain.models.Person

data class SelectedPersonItemModel(val person: Person, val selected: Boolean = false) {
    val name = person.displayName
}
