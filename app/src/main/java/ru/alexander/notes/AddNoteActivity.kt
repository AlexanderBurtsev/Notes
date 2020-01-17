package ru.alexander.notes

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.add_note_activity_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, thisYear, thisMonth, thisDay ->
                calendar.set(thisYear, thisMonth, thisDay)
                etDate.setText(SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(calendar.time))
            }, year, month, day)

            datePickerDialog.show()
        }

        btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, thisHour, thisMinute ->
                val time = "${"%02d".format(thisHour)}:${"%02d".format(thisMinute)}"
                etTime.setText(time)
            }, hour, minute, true)

            timePickerDialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ok) {
            if (etName.text.isEmpty()) {
                Toast.makeText(this, "Введите имя заметки", Toast.LENGTH_SHORT).show()
            } else {
                val name = etName.text.toString()
                val date = "${etDate.text} ${etTime.text}"
                val description = etDescription.text.toString()
                val creationTime = SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault()).format(Date())

                ApplicationClass.notes.add(Note(name, date, description, creationTime))

                setResult(Activity.RESULT_OK)
                this.finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
