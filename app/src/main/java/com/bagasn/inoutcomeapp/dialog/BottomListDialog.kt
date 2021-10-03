package com.bagasn.inoutcomeapp.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bagasn.inoutcomeapp.R
import com.bagasn.inoutcomeapp.adapter.RecyclerDialogListAdapter
import com.bagasn.inoutcomeapp.db.model.SimpleModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomListDialog(
    private val title: String,
    private val itemList: List<SimpleModel>,
    private val editable: Boolean = false,
    private val onAdd: (() -> Unit)? = null,
    private val onSelect: (item: SimpleModel) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var textTitle: TextView
    private lateinit var recyclerContent: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_bottom_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<ImageView>(R.id.button_action_toolbar)?.let {
            it.setImageResource(R.drawable.ic_baseline_close_24)
        }

        textTitle = view.findViewById(R.id.title_toolbar)
        textTitle.text = title

        recyclerContent = view.findViewById(R.id.recycler_content)
        recyclerContent.adapter = RecyclerDialogListAdapter(
            requireContext(),
            itemList,
            editable,
            onAddClick = {
                onAdd?.let {
                    it()
                    dismiss()
                }
            },
            onSelect = { item ->
                onSelect(item)
                dismiss()
            }
        )

        view.findViewById<View>(R.id.button_action_toolbar).setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

}