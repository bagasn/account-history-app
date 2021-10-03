package com.bagasn.inoutcomeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bagasn.inoutcomeapp.R
import com.bagasn.inoutcomeapp.db.model.SimpleModel
import java.util.*

class RecyclerDialogListAdapter(
    private val context: Context,
    private val objects: List<SimpleModel>,
    private val editable: Boolean,
    private val onAddClick: () -> Unit,
    private val onSelect: (item: SimpleModel) -> Unit
) : RecyclerView.Adapter<RecyclerDialogListAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var layoutResource = R.layout.item_simple_list
        if (viewType == 1) layoutResource = R.layout.item_add_to_list

        val view = LayoutInflater.from(context)
            .inflate(layoutResource, parent, false)

        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            holder.description.setOnClickListener { onAddClick() }
        } else {
            val item = objects[position]
            holder.description.text = item.value
            holder.description.setOnClickListener {
                onSelect(item)
            }
        }
    }

    override fun getItemCount(): Int = objects.size + (if (editable) 1 else 0)

    override fun getItemViewType(position: Int): Int = if (position == objects.size) {
        if (editable) 1 else 0
    } else {
        0
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description: TextView = itemView.findViewById(R.id.text_content)
    }

}