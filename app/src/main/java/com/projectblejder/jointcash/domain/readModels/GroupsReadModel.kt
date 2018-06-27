package com.projectblejder.jointcash.domain.readModels

import android.content.Context
import com.projectblejder.jointcash.domain.Groups
import com.projectblejder.jointcash.domain.models.Group
import com.projectblejder.jointcash.infrastructure.KeywordMapper
import com.projectblejder.jointcash.infrastructure.dao.GroupsDao
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GroupsReadModel
@Inject constructor(
        private val context: Context,
        private val groupsDao: GroupsDao,
        private val groups: Groups,
        private val mapper: KeywordMapper
) {

    fun selectedGroup(): Single<Group> {
        val pref = context.getSharedPreferences("user-data", Context.MODE_PRIVATE)
        val id = pref.getInt("selected-group", 1)
        return groupsDao.findById(id)
                .subscribeOn(Schedulers.io())
                .map { Group(it.id, mapper.map(it.name)) }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun observe(): Flowable<List<Group>> {
        return groups.observe()
    }
}
