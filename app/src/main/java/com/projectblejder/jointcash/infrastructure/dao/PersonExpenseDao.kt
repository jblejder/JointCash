package com.projectblejder.jointcash.infrastructure.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.projectblejder.jointcash.infrastructure.models.PersonExpense

@Dao
abstract class PersonExpenseDao {

    @Insert
    abstract fun addPersonExpense(relation: PersonExpense)

    @Query("DELETE FROM person_expenses WHERE expense_id = :expenseId")
    abstract fun removePersonFromExpense(expenseId: Int)

    @Query("SELECT * FROM person_expenses WHERE  expense_id = :expenseId")
    abstract fun allForExpense(expenseId: Int): List<PersonExpense>
}
