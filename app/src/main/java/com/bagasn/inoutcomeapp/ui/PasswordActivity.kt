package com.bagasn.inoutcomeapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bagasn.inoutcomeapp.R
import com.bagasn.inoutcomeapp.app.AppActivity

class PasswordActivity: AppActivity() {

    private var _inputtedPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
    }

    fun onKeyPadClick(view: View) {
        when (view.id) {
            R.id.pad_0 -> inputPassword(0)
            R.id.pad_1 -> inputPassword(1)
            R.id.pad_2 -> inputPassword(2)
            R.id.pad_3 -> inputPassword(3)
            R.id.pad_4 -> inputPassword(4)
            R.id.pad_5 -> inputPassword(5)
            R.id.pad_6 -> inputPassword(6)
            R.id.pad_7 -> inputPassword(7)
            R.id.pad_8 -> inputPassword(8)
            R.id.pad_9 -> inputPassword(9)
        }
    }

    private fun inputPassword(num: Int) {
        _inputtedPassword += num
        if (_inputtedPassword.length >= 4) {
            if (_inputtedPassword == "5689") {
                moveToHome()
            }
        }
    }

    private fun moveToHome() {
        startActivity(Intent(this, OutComeHistory::class.java))
    }

}