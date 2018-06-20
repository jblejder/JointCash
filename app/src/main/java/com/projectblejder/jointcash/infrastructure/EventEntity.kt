package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        @ColumnInfo(name = "name")
        var name: String
)
