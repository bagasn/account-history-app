package com.bagasn.inoutcomeapp.app

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bagasn.inoutcomeapp.db.AppDatabase

abstract class AppActivity : AppCompatActivity() {

    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDatabase = AppDatabase(applicationContext)
    }

    fun formEmptyField(view: EditText, errorMessage: String, default: Boolean): Boolean {
        if (view.text.isBlank()) {
            view.error = errorMessage
            return false
        }
        return default
    }

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(applicationContext, message, duration).show()
    }

}