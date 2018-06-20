package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface PersonsDao {

    @Query("SELECT * FROM persons")
    fun all(): Flowable<List<PersonEntity>>

    @Insert
    fun create(person: PersonEntity)
}
