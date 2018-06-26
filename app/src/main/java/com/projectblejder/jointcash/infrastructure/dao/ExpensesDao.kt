package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.projectblejder.jointcash.infrastructure.models.Expense

@Dao
abstract class ExpensesDao {

    @Insert
    abstract fun create(expense: Expense)

    @Query("DELETE FROM expenses WHERE id = :id")
    abstract fun delete(id: Int)

    @Query("SELECT * FROM expenses WHERE group_id = :groupId")
    abstract fun allExpensesInGroup(groupId: Int): List<Expense>
}
