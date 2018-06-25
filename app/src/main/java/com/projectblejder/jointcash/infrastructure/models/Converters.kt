package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromExpenseTypeToString(type: ExpenseType) = when (type) {
        ExpenseType.Pay -> "pay"
        ExpenseType.Debt -> "debt"
    }
}