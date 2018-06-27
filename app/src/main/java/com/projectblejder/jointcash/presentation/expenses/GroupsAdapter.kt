package com.projectblejder.jointcash.presentation.expenses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.GroupListItemBinding
import com.projectblejder.jointcash.domain.models.Group

class GroupsAdapter : RecyclerView.Adapter<GroupViewHolder>() {

    var dataSet: List<Group> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GroupListItemBinding.inflate(inflater, parent, false)
        return GroupViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.binding.name.text = dataSet[position].name
    }
}

class GroupViewHolder(val binding: GroupListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}