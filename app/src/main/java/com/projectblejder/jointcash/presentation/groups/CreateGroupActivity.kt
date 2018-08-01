package com.projectblejder.jointcash.presentation.groups


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.projectblejder.jointcash.R
import com.projectblejder.jointcash.databinding.CreateGroupActivityBinding
import com.projectblejder.jointcash.presentation.utils.extensions.disposeWith
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CreateGroupActivity : AppCompatActivity() {

    val adapter = GroupsAdapter()

    val disposeBag = CompositeDisposable()

    @Inject
    lateinit var viewModel: CreateGroupViewModel

    lateinit var binding: CreateGroupActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.create_group_activity)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.selects
                .subscribe { viewModel.updateSelected(this) }
                .disposeWith(disposeBag)

        viewModel.persons
                .subscribe {
                    adapter.data = it
                }.disposeWith(disposeBag)
    }
}
