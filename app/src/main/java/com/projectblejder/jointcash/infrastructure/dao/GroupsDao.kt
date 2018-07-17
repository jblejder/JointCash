package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.projectblejder.jointcash.infrastructure.GroupCreationModel
import com.projectblejder.jointcash.infrastructure.models.Group
import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.infrastructure.models.PersonGroup
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class GroupsDao {

    @Query("SELECT * FROM groups")
    abstract fun allRx(): Flowable<List<Group>>

    @Query("SELECT * FROM groups")
    abstract fun all(): List<Group>

    @Insert
    abstract fun create(group: Group): Long

    @Query("SELECT * FROM groups WHERE id = :id")
    abstract fun findById(id: Int): Single<Group>

    @Query("DELETE FROM groups WHERE id = :id")
    abstract fun deleteById(id: Int)

    @Transaction
    open fun createGroup(model: GroupCreationModel) {
        val newGroupId = create(Group(id = null, name = model.name))
        model.participants.forEach { addPersonToGroup(it, newGroupId) }
    }

    @Insert
    abstract fun addPersonToGroup(relation: PersonGroup)

    fun addPersonToGroup(personId: Long, groupId: Long) {
        addPersonToGroup(PersonGroup(id = null, personId = personId, groupId = groupId))
    }

    @Query("SELECT * FROM persons INNER JOIN person_group ON persons.id = person_id WHERE group_id = :groupId")
    abstract fun personsInGroup(groupId: Int): List<Person>

    @Query("DELETE FROM person_group WHERE person_id = :personId AND group_id = :groupId")
    abstract fun removePersonFromGroup(personId: Int, groupId: Int)
}
