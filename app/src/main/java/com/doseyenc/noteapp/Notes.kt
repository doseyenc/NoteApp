package com.doseyenc.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Notes(
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "text")
    val noteText: String
) {
    @PrimaryKey(autoGenerate = true)
    var id =0
}