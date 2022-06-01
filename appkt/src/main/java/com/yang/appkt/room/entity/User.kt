package com.yang.appkt.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "user_info")
data class User(
    var name: String,
    var age: Int,
    var info: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
