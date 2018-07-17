package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        @ColumnInfo(name = "display_name")
        var displayName: String
)
