package com.projectblejder.jointcash.presentation.persons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.PersonListItemBinding
import com.projectblejder.jointcash.domain.models.Person

class PersonsAdapter : RecyclerView.Adapter<PersonViewHolder>() {

    var dataSet: List<Person> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonListItemBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.name.text = dataSet[position].displayName
    }
}

class PersonViewHolder(val binding: PersonListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
