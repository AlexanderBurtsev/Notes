package ru.alexander.notes

import android.app.Application

class ApplicationClass : Application() {

    companion object {
        lateinit var notes: MutableList<Note>
    }

    override fun onCreate() {
        super.onCreate()

        notes = ArrayList()
    }
}