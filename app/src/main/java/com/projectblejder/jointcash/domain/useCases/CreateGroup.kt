package com.projectblejder.jointcash.domain.useCases

import com.projectblejder.jointcash.domain.Groups
import com.projectblejder.jointcash.domain.models.Group

class CreateGroup(
        val groups: Groups
) {
    fun execute(group: Group) {
        groups.saveGroup(group)
    }
}
