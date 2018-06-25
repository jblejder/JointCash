package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.*

@Entity(
        tableName = "expenses",
        foreignKeys = [
            ForeignKey(entity = Group::class,
                    parentColumns = ["id"],
                    childColumns = ["group_id"])
        ]
)
data class Expense(
        @PrimaryKey
        var id: Int?,
        @ColumnInfo(name = "name")
        var name: String,
        @ColumnInfo(name = "group_id")
        var groupId: Int?
)

