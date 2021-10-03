package com.bagasn.inoutcomeapp.ui

import android.content.Intent
import android.os.Bundle
import com.bagasn.inoutcomeapp.R
import com.bagasn.inoutcomeapp.app.AppActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class SplashActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.async {
            delay(8000)
            moveToNextPage()
        }.start()
    }

    override fun onBackPressed() {}

    private fun moveToNextPage() {
        startActivity(Intent(this, PasswordActivity::class.java))
    }

}