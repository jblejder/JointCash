package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.projectblejder.jointcash.infrastructure.models.Group
import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.infrastructure.models.PersonGroup
import io.reactivex.Flowable

@Dao
abstract class GroupsDao {

    @Query("SELECT * FROM groups")
    abstract fun all(): Flowable<List<Group>>

    @Insert
    abstract fun create(group: Group)

    @Query("DELETE FROM groups WHERE id = :id")
    abstract fun deleteById(id: Int)
}
