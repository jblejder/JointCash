package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "person_expenses",
        foreignKeys = [
            (ForeignKey(entity = Expense::class, parentColumns = ["id"], childColumns = ["expense_id"])),
            (ForeignKey(entity = Person::class, parentColumns = ["id"], childColumns = ["person_id"]))
        ])
data class PersonExpense(
        @PrimaryKey
        var id: Long?,

        @ColumnInfo(name = "expense_id")
        var expenseId: Long,
        @ColumnInfo(name = "person_id")
        var personId: Long,
        @ColumnInfo(name = "amount")

        var amount: Double,
        @ColumnInfo(name = "type")
        var type: ExpenseType
)