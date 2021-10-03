package com.bagasn.inoutcomeapp.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.bagasn.inoutcomeapp.R

class AddItemDialog(
    private val onSubmit: (text: String) -> Unit
) : DialogFragment() {

    private lateinit var inputText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputText = view.findViewById(R.id.input_description)

        view.findViewById<Button>(R.id.button_cancel)?.setOnClickListener {
            dismissAllowingStateLoss()
        }
        view.findViewById<Button>(R.id.button_submit)?.setOnClickListener {
            if (inputText.text.isBlank()) {
                inputText.error = "Enter description"
                inputText.requestFocus()
                return@setOnClickListener
            }


            val text = inputText.text.toString()
            onSubmit(text)

            dismiss()
        }

    }

}