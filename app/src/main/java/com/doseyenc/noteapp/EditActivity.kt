package com.doseyenc.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.doseyenc.noteapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    lateinit var viewModel: NoteViewModel
    var noteID = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")

        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteText")
            noteID = intent.getIntExtra("noteId", -1)
            binding.buttonSave.setText("Güncelle")
            binding.TvNoteTitle.setText(noteTitle)
            binding.tVNoteText.setText(noteDescription)
        } else {
            binding.buttonSave.setText("Kaydet")
        }
        binding.buttonSave.setOnClickListener {

            val noteTitle = binding.TvNoteTitle.text.toString()
            val noteText = binding.tVNoteText.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteText.isNotEmpty()) {

                    val updatedNote = Notes(noteTitle, noteText)
                    updatedNote.id = noteID
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(this, "Not Güncellendi..", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteText.isNotEmpty()) {

                    viewModel.addNote(Notes(noteTitle, noteText))
                    Toast.makeText(this, "$noteTitle Eklendi", Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}