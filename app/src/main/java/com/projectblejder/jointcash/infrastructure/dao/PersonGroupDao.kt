package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.infrastructure.models.PersonGroup

@Dao
abstract class PersonGroupDao {

    @Insert
    abstract fun addPersonToGroup(relation: PersonGroup)

    fun addPersonToGroup(personId: Int, groupId: Int) {
        addPersonToGroup(PersonGroup(null, personId, groupId))
    }

    @Query("SELECT * FROM persons INNER JOIN person_group ON persons.id = person_id WHERE group_id = :groupId")
    abstract fun personsInGroup(groupId: Int): List<Person>

    @Query("DELETE FROM person_group WHERE person_id = :personId AND group_id = :groupId")
    abstract fun removePersonFromGroup(personId: Int, groupId: Int)
}
