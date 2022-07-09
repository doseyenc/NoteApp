package com.doseyenc.noteapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doseyenc.noteapp.databinding.NoteCardviewDesignBinding
import com.doseyenc.noteapp.model.Notes
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

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
            val rnd = Random()
            val currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            binding.noteCardView.background.setTint(currentColor)

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