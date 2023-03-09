package edu.mines.csci448.examples.samodelkin

import android.app.Application
import android.util.Log

class SamodelkinApplication : Application() {
    companion object {
        private const val LOG_TAG = "448.SamodelkinApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate() called")
    }
}