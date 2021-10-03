package com.bagasn.inoutcomeapp.ui

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bagasn.inoutcomeapp.R
import com.bagasn.inoutcomeapp.app.AppActivity
import com.bagasn.inoutcomeapp.db.model.AccountHistoryModel
import com.bagasn.inoutcomeapp.db.model.CategoryAccountModel
import com.bagasn.inoutcomeapp.db.model.SimpleModel
import com.bagasn.inoutcomeapp.db.table.TableAccountHistory
import com.bagasn.inoutcomeapp.db.table.TableCategoryAccount
import com.bagasn.inoutcomeapp.dialog.AddItemDialog
import com.bagasn.inoutcomeapp.dialog.BottomListDialog
import com.bagasn.inoutcomeapp.util.FormatterUtil
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class CreateOutcome : AppActivity() {

    private lateinit var textDescription: TextInputEditText
    private lateinit var textValue: TextInputEditText
    private lateinit var textCategory: TextInputEditText


    private var selectedCategory: SimpleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_outcome)

        initContentView()

        findViewById<View>(R.id.fab_submit).setOnClickListener {
            if (!isFormValid()) return@setOnClickListener

            val description = textDescription.text.toString().trim()
            val outcome = textValue.text.toString().toInt()
            val category = selectedCategory?.key

            if (category != null) {
                insertOutcome(description, outcome, category)
            }
        }
    }

    private fun initContentView() {
        textDescription = findViewById(R.id.edt_description)
        textValue = findViewById(R.id.edt_outcome)
        textCategory = findViewById(R.id.edt_category)

        textCategory.setOnClickListener {
            showCategoryListDialog()
        }
    }

    private fun showCategoryListDialog() {
        BottomListDialog(
            "Category Outcome",
            getListCategory(),
            editable = true,
            onAdd = {
                showDialogAddItem()
            },
            onSelect = {
                selectedCategory = it
                textCategory.setText(it.value)
                textCategory.error = null
            }
        ).show(supportFragmentManager, "dialog_list_category")
    }

    private fun showDialogAddItem() {
        AddItemDialog { category ->
            appDatabase.let {
                val db = it.getDatabase(true)
                val model = CategoryAccountModel(description = category)
                val message = if (TableCategoryAccount.add(db, model) == -1L) {
                    "Failed to add category"
                } else {
                    showCategoryListDialog()
                    null
                }
                db.close()

                if (message != null) {
                    showToast(message)
                }
            }
        }.show(supportFragmentManager, "dialog_add_category")
    }

    private fun isFormValid(): Boolean {
        var isValid = true

        isValid = formEmptyField(
            textDescription,
            "Description cannot be empty",
            isValid
        )

        isValid = formEmptyField(
            textValue,
            "Enter outcome",
            isValid
        )

        isValid = formEmptyField(
            textCategory,
            "Fill outcome category",
            isValid
        )

        return isValid
    }

    private fun insertOutcome(description: String, outcome: Int, category: String) {
        val datetime = FormatterUtil.dateToString(
            Calendar.getInstance(),
            FormatterUtil.PATTERN_DATE_TIME
        )

        if (datetime != null) {
            val model = AccountHistoryModel(
                null,
                description,
                outcome,
                category,
                datetime
            )

            appDatabase.let {
                val db = it.getDatabase(true)
                val message = if (TableAccountHistory.add(db, model) == -1L) {
                    "Failed to insert outcome"
                } else {
                    finish()
                    "Outcome has added"
                }
                db.close()

                Toast.makeText(
                    this,
                    message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getListCategory(): List<SimpleModel> {
        val list = mutableListOf<SimpleModel>()

        appDatabase.let {
            val db = it.getDatabase(false)
            val rows = TableCategoryAccount.get(db)

            for (row in rows) {
                if (row.id != null) {
                    list.add(
                        SimpleModel(
                            row.id.toString(),
                            row.description
                        )
                    )
                }
            }

            db.close()
        }

        return list
    }

}