package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.projectblejder.jointcash.infrastructure.models.Person
import io.reactivex.Flowable

@Dao
interface PersonsDao {

    @Query("SELECT * FROM persons")
    fun allRx(): Flowable<List<Person>>

//    fun allRx() = _allRx().map { it.map { if (it.displayName == "{you}") Person(it.id, "You") else it } }

    @Query("SELECT * FROM persons")
    fun all(): List<Person>

    @Insert
    fun create(person: Person)

    @Query("DELETE FROM persons WHERE id = :id")
    fun deleteById(id: Int)
}
