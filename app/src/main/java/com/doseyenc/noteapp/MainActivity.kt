package com.doseyenc.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.doseyenc.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RVAdapter(this, this, this);
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, EditActivity::class.java))
            this.finish()
        }


    }

    override fun onDeleteIconClick(note: Notes) {
        viewModel.deleteNote(note)
    }

    override fun onNoteClick(note: Notes) {
        startActivity(
            Intent(
                this@MainActivity,
                EditActivity::class.java
            )
                .putExtra("noteType", "Edit")
                .putExtra("noteTitle", note.noteTitle)
                .putExtra("noteText", note.noteText)
                .putExtra("noteId", note.id)
        )
        this.finish()
    }
}