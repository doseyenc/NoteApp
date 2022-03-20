package com.doseyenc.noteapp

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {
    val notesAll:LiveData<List<Notes>> = notesDao.getAll()

    suspend fun insert(notes: Notes){
        notesDao.ınsert(notes)
    }
    suspend fun update(notes: Notes){
        notesDao.update(notes)
    }
    suspend fun delete(notes: Notes){
        notesDao.delete(notes)
    }
}