package com.projectblejder.jointcash.presentation.expenses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projectblejder.jointcash.R

class ExpensesAdapter : RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>() {

    var feed = listOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExpensesViewHolder(inflater.inflate(R.layout.expenses_fragment, parent, false))
    }

    override fun getItemCount() = feed.size

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.textView).setText(feed[position])
    }

    class ExpensesViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
