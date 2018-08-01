package com.projectblejder.jointcash.presentation.groups

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.projectblejder.jointcash.R
import com.projectblejder.jointcash.databinding.ParticipantItemViewBinding
import io.reactivex.subjects.PublishSubject

class GroupsAdapter : RecyclerView.Adapter<GroupsAdapter.ViewHolder>() {

    var data: List<SelectedPersonItemModel> = emptyList()
        set(value) {
            field = data
            notifyDataSetChanged()
        }

    val selects = PublishSubject.create<SelectedPersonItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ParticipantItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.participant_item_view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindModel(data[position])
    }

    inner class ViewHolder(val binding: ParticipantItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var model: SelectedPersonItemModel? = null

        init {
            binding.root.setOnClickListener {
                model?.also { selects.onNext(it) }
            }
        }

        fun bindModel(model: SelectedPersonItemModel) {
            this.model = model
            binding.name.text = model.name
            binding.selected.isChecked = model.selected
        }
    }
}
