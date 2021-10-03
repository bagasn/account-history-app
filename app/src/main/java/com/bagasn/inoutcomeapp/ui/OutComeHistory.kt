package com.bagasn.inoutcomeapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bagasn.inoutcomeapp.R
import com.bagasn.inoutcomeapp.adapter.RecyclerAccountHistoryAdapter
import com.bagasn.inoutcomeapp.app.AppActivity
import com.bagasn.inoutcomeapp.db.model.AccountHistoryModel
import com.bagasn.inoutcomeapp.db.model.CategoryAccountModel
import com.bagasn.inoutcomeapp.db.table.TableAccountHistory
import com.bagasn.inoutcomeapp.db.table.TableCategoryAccount

class OutComeHistory : AppActivity() {

    private lateinit var recyclerContentView: RecyclerView

    private val _categories = mutableListOf<CategoryAccountModel>()
    private val _historyAccount = mutableListOf<AccountHistoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outcome_history)

        recyclerContentView = findViewById(R.id.recycler_content)
        recyclerContentView.adapter = RecyclerAccountHistoryAdapter(
            this,
            _categories,
            _historyAccount
        )

        findViewById<View>(R.id.fab_add).setOnClickListener {
            val intent = Intent(applicationContext, CreateOutcome::class.java)
            startActivity(intent)
        }

        getCategoryAccount()
        getContentAccountHistory()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_cart_account_history, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            else -> {
                showToast("feature is coming soon")
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getContentAccountHistory() {
        appDatabase.let {
            val db = it.getDatabase(false)

            val rows = TableAccountHistory.get(db)
            _historyAccount.addAll(rows)

            db.close()
        }
    }

    private fun getCategoryAccount() {
        appDatabase.let {
            val db = it.getDatabase(false)
            val rows = TableCategoryAccount.get(db)

            _categories.addAll(rows)

            db.close()
        }
    }

}