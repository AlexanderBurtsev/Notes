package ru.alexander.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*

class NoteAdapter (context: Context, private val notes: MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val activity: ItemClicked = context as ItemClicked

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvName.text = notes[position].name
            tvCreationTime.text = notes[position].creationTime
            itemView.tag = notes[position]
            itemView.setOnClickListener {
                activity.onItemClicked(notes.indexOf(it.tag as Note))
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tvName
        val tvCreationTime: TextView = itemView.tvCreationTime
    }
}