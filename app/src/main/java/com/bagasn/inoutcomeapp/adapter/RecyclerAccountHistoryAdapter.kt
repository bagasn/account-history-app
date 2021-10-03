package com.bagasn.inoutcomeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bagasn.inoutcomeapp.R
import com.bagasn.inoutcomeapp.db.model.AccountHistoryModel
import com.bagasn.inoutcomeapp.db.model.CategoryAccountModel
import com.bagasn.inoutcomeapp.util.FormatterUtil
import com.bagasn.inoutcomeapp.util.ResourceUtil

class RecyclerAccountHistoryAdapter(
    private val context: Context,
    private val categories: List<CategoryAccountModel>,
    private val objects: List<AccountHistoryModel>
) : RecyclerView.Adapter<RecyclerAccountHistoryAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var view = LayoutInflater.from(context)
            .inflate(R.layout.item_account_history, parent, false)

        if (viewType == 1) {
            view = getLastView()
        }

        return ItemHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            val item = objects[position]

            holder.apply {
                description.text = item.description
                category.text = getCategory(item.category.toInt())
                value.text = FormatterUtil.longToSeparateNumber(item.value.toLong())

                val calendar = FormatterUtil.stringToCalendar(
                    item.datetime,
                    FormatterUtil.PATTERN_DATE_TIME
                )

                calendar?.let {
                    date.text = FormatterUtil.dateToString(it, FormatterUtil.PATTERN_OUTPUT_DATE)
                }
            }
        }
    }

    override fun getItemCount(): Int = objects.size + 1

    override fun getItemViewType(position: Int): Int = if (position == objects.size) {
        1
    } else {
        0
    }

    private fun getCategory(id: Int): String {
        for (item in categories) {
            if (item.id == id) return item.description
        }
        return id.toString()
    }

    private fun getLastView(): View {
        val view = View(context)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            ResourceUtil.pixelToDip(context.resources, 52f)
        )
        view.layoutParams = layoutParams

        return view
    }

    inner class ItemHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        lateinit var description: TextView
        lateinit var date: TextView
        lateinit var category: TextView
        lateinit var value: TextView

        init {
            if (viewType == 0) {
                description = itemView.findViewById(R.id.text_description)
                date = itemView.findViewById(R.id.text_date)
                category = itemView.findViewById(R.id.text_category)
                value = itemView.findViewById(R.id.text_value)
            }
        }
    }
}