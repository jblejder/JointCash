package com.projectblejder.jointcash.infrastructure

import com.projectblejder.jointcash.domain.Groups
import com.projectblejder.jointcash.infrastructure.dao.GroupsDao
import io.reactivex.Flowable
import javax.inject.Inject

private typealias DomainGroup = com.projectblejder.jointcash.domain.models.Group
private typealias InfraGroup = com.projectblejder.jointcash.infrastructure.models.Group

class Groups
@Inject constructor(
        private val mapper: KeywordMapper,
        private val groupsDao: GroupsDao
) : Groups {

    override fun observe(): Flowable<List<DomainGroup>> {
        return groupsDao.allRx()
                .map(::map)
    }

    private fun map(groups: List<InfraGroup>): List<DomainGroup> {
        return groups.map(::mapGroup)
    }

    private fun mapGroup(group: InfraGroup): DomainGroup {
        return DomainGroup(
                id = group.id,
                name = mapper.map(group.name)
        )
    }
}
