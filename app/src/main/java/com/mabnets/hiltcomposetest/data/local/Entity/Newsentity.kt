package com.mabnets.hiltcomposetest.data.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="news")
data class Newsentity(
    @PrimaryKey val id: Int,
    val links: String,
    val title: String,
    val type: String,
    val description: String,
    val imagelink:String

)