package com.projectblejder.jointcash.infrastructure.converters

import android.arch.persistence.room.TypeConverter
import com.projectblejder.jointcash.infrastructure.models.ExpenseType

class ExpenseTypeConverters {

    @TypeConverter
    fun fromTypeToString(type: ExpenseType): String = when (type) {
        ExpenseType.Pay -> "pay"
        ExpenseType.Debt -> "debt"
    }

    @TypeConverter
    fun fromStringToType(string: String): ExpenseType = when (string) {
        "pay" -> ExpenseType.Pay
        "debt" -> ExpenseType.Debt
        else -> throw IllegalStateException("$string is unknown Expense Type")
    }
}