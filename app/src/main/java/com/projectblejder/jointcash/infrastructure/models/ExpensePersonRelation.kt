package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "expense_person_relations",
        foreignKeys = [
            (ForeignKey(entity = Expense::class, parentColumns = ["id"], childColumns = ["expense_id"])),
            (ForeignKey(entity = Person::class, parentColumns = ["id"], childColumns = ["person_id"]))
        ])
data class ExpensePersonRelation(
        @PrimaryKey
        var id: Int?,

        @ColumnInfo(name = "expense_id")
        var expenseId: Int,
        @ColumnInfo(name = "person_id")
        var personId: Int,
        @ColumnInfo(name = "amount")

        var amount: Double,
        @ColumnInfo(name = "type")
        var type: ExpenseType
)