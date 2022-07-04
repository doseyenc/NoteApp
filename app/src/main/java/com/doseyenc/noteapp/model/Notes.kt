package com.doseyenc.noteapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notesTable")
data class Notes(
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "text")
    val noteText: String
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id =0
}