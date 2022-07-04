package com.doseyenc.noteapp.ui.mainScreen

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doseyenc.noteapp.NoteClickDeleteInterface
import com.doseyenc.noteapp.NoteClickInterface
import com.doseyenc.noteapp.R
import com.doseyenc.noteapp.RVAdapter
import com.doseyenc.noteapp.databinding.FragmentMainBinding
import com.doseyenc.noteapp.model.Notes


class MainFragment : Fragment(R.layout.fragment_main), NoteClickDeleteInterface,
    NoteClickInterface {
    lateinit var viewModel: NoteViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = RVAdapter(requireContext(), this, this);
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance( this.activity!!.application )
        ).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToEditFragment("add")
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteIconClick(note: Notes) {
        viewModel.deleteNote(note)
    }

    override fun onNoteClick(note: Notes) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToEditFragment("update",note)
        )
    }
}