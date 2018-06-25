package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.projectblejder.jointcash.infrastructure.models.Person
import io.reactivex.Flowable

@Dao
interface PersonsDao {

    @Query("SELECT * FROM persons")
    fun all(): Flowable<List<Person>>

    @Insert
    fun create(person: Person)

    @Query("DELETE FROM persons WHERE id = :id")
    fun deleteById(id: Int)
}
