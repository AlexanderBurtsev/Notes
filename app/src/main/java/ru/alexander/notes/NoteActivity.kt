package ru.alexander.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.note_activity_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val index = intent.getIntExtra("index", 0)

        tvName.text = ApplicationClass.notes[index].name
        tvDate.text = ApplicationClass.notes[index].date
        tvDescription.text = ApplicationClass.notes[index].description
    }
}
