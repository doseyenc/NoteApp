package com.doseyenc.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun ınsert(note: Notes)

    @Update()
    suspend fun update(note: Notes)

    @Delete()
    suspend fun delete(note: Notes)

    @Query("Select * from notesTable order by id ASC")
    fun getAll():LiveData<List<Notes>>
}