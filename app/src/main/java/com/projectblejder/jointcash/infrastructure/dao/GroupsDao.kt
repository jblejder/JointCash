package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.projectblejder.jointcash.infrastructure.models.Group
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class GroupsDao {

    @Query("SELECT * FROM groups")
    abstract fun allRx(): Flowable<List<Group>>

    @Query("SELECT * FROM groups")
    abstract fun all(): List<Group>

    @Insert
    abstract fun create(group: Group)

    @Query("SELECT * FROM groups WHERE id = :id")
    abstract fun findById(id: Int): Single<Group>

    @Query("DELETE FROM groups WHERE id = :id")
    abstract fun deleteById(id: Int)
}
