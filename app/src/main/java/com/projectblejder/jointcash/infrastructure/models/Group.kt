package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "groups"
)
data class Group(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        @ColumnInfo(name = "name")
        var name: String
)
