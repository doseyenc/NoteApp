package com.doseyenc.noteapp.ui.editScreen

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.doseyenc.noteapp.R
import com.doseyenc.noteapp.databinding.FragmentEditBinding
import com.doseyenc.noteapp.databinding.FragmentMainBinding
import com.doseyenc.noteapp.model.Notes
import com.doseyenc.noteapp.ui.mainScreen.MainActivity
import com.doseyenc.noteapp.ui.mainScreen.NoteViewModel


class EditFragment : Fragment(R.layout.fragment_edit) {
    lateinit var viewModel: NoteViewModel
    var noteID = -1;
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance( this.activity!!.application)
        ).get(NoteViewModel::class.java)

        val noteType = arguments?.get("NoteType")

        if (noteType!! == "update") {
            val note = arguments?.get("editCreateNote") as Notes
            val noteTitle = note.noteTitle
            val noteDescription = note.noteText
            noteID = note.id

            binding.buttonSave.text="Güncelle"
            binding.TvNoteTitle.setText(noteTitle)
            binding.tVNoteText.setText(noteDescription)
        } else {
            binding.buttonSave.text= "Kaydet"
        }
        binding.buttonSave.setOnClickListener {

            val noteTitle = binding.TvNoteTitle.text.toString()
            val noteText = binding.tVNoteText.text.toString()

            if (noteType == "update") {
                if (noteTitle.isNotEmpty() && noteText.isNotEmpty()) {

                    val updatedNote = Notes(noteTitle, noteText)
                    updatedNote.id = noteID
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(requireContext(), "Not Güncellendi..", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteText.isNotEmpty()) {

                    viewModel.addNote(Notes(noteTitle, noteText))
                    Toast.makeText(requireContext(), "$noteTitle Eklendi", Toast.LENGTH_SHORT).show()
                }
            }
            findNavController().navigate(
                EditFragmentDirections.actionEditFragmentToMainFragment()
            )
        }
    }


}