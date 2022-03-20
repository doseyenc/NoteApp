package com.doseyenc.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    val allNotes:LiveData<List<Notes>>
    val repo :NoteRepository
    init {
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repo = NoteRepository(dao)
        allNotes=repo.notesAll
    }
    fun deleteNote (note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }

    fun updateNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(note)
    }

    fun addNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }
}