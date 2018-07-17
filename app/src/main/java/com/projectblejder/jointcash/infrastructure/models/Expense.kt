package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "expenses",
        foreignKeys = [
            ForeignKey(entity = Group::class,
                    parentColumns = ["id"],
                    childColumns = ["group_id"])
        ]
)
data class Expense(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,

        @ColumnInfo(name = "name")
        var name: String,
        @ColumnInfo(name = "group_id")
        var groupId: Int?
)

