package com.projectblejder.jointcash.presentation.expenses

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.GroupsDialogFragmentBinding
import com.projectblejder.jointcash.domain.readModels.GroupsReadModel
import com.projectblejder.jointcash.presentation.groups.CreateGroupActivity
import com.projectblejder.jointcash.presentation.shared.uiComponents.CustomBottomSheetDialogFragment
import com.projectblejder.jointcash.presentation.utils.extensions.disposeWith
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GroupsDialogFragment : CustomBottomSheetDialogFragment() {

    lateinit var binding: GroupsDialogFragmentBinding

    @Inject
    lateinit var groups: GroupsReadModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return GroupsDialogFragmentBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = GroupsAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        groups.observe()
                .onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { adapter.dataSet = it }
                .disposeWith(disposeBag)

        binding.createNewGroupButton.setOnClickListener {
            startActivity(Intent(context, CreateGroupActivity::class.java))
        }
    }
}
