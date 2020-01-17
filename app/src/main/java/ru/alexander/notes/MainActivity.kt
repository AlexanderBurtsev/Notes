package ru.alexander.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemClicked {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdater: RecyclerView.Adapter<NoteAdapter.ViewHolder>
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = listNotes
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        customAdater = NoteAdapter(this, ApplicationClass.notes)
        recyclerView.adapter = customAdater

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            customAdater.notifyDataSetChanged()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onItemClicked(index: Int) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }
}
