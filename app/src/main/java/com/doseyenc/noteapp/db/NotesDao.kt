package com.doseyenc.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.doseyenc.noteapp.model.Notes

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