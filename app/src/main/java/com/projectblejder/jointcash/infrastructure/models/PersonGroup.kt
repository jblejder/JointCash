package com.projectblejder.jointcash.infrastructure.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "person_group",
        foreignKeys = [
            ForeignKey(entity = Person::class, parentColumns = ["id"], childColumns = ["person_id"]),
            ForeignKey(entity = Group::class, parentColumns = ["id"], childColumns = ["group_id"])
        ])
data class PersonGroup(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        @ColumnInfo(name = "person_id")
        var personId: Int,
        @ColumnInfo(name = "group_id")
        var groupId: Int
)
