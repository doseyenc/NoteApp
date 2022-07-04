package com.doseyenc.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doseyenc.noteapp.databinding.NoteCardviewDesignBinding
import com.doseyenc.noteapp.model.Notes

class RVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Notes>()

    inner class ViewHolder(val binding: NoteCardviewDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NoteCardviewDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            binding.textViewNoteText.setText(allNotes.get(position).noteTitle)

            binding.imageViewDelete.setOnClickListener {
                noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
            }

            itemView.setOnClickListener {
                noteClickInterface.onNoteClick(allNotes.get(position))
            }
        }
    }

    override fun getItemCount(): Int = allNotes.size


    fun updateList(newList: List<Notes>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Notes)
}

interface NoteClickInterface {
    fun onNoteClick(note: Notes)
}