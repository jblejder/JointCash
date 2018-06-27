package com.projectblejder.jointcash.domain.readModels

import com.projectblejder.jointcash.domain.Groups
import com.projectblejder.jointcash.domain.models.Group
import io.reactivex.Flowable
import javax.inject.Inject

class GroupsReadModel
@Inject constructor(
        private val persons: Groups
) {

    fun observe(): Flowable<List<Group>> {
        return persons.observe()
    }
}
