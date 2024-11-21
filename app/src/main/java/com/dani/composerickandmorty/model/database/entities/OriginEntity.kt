package com.dani.composerickandmorty.model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OriginEntity(
    @PrimaryKey (autoGenerate = false)
    val id: Int,
    val name: String,
    val url: String
)