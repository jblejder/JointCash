package com.projectblejder.jointcash.domain

import com.projectblejder.jointcash.domain.models.Group
import io.reactivex.Flowable

interface Groups {

    fun observe(): Flowable<List<Group>>
    fun saveGroup(group: Group)
}
