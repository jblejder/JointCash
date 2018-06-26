package com.projectblejder.jointcash.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.projectblejder.jointcash.infrastructure.AppDatabaseFactory
import com.projectblejder.jointcash.infrastructure.AppDatabaseProvider
import com.projectblejder.jointcash.presentation.utils.extensions.disposeWith
import dagger.android.AndroidInjection
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseProvider: AppDatabaseProvider

    @Inject
    lateinit var databaseFactory: AppDatabaseFactory

    val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(View(this))

        initDatabase()
                .doOnComplete { openNextActivity() }
                .subscribe()
                .disposeWith(disposeBag)
    }

    private fun initDatabase(): Completable =
            Completable.complete()
                    .doOnComplete {
                        databaseProvider.initialize(databaseFactory)
                    }.subscribeOn(Schedulers.io())

    private fun openNextActivity() {
        startActivity(Intent(this, AppActivity::class.java))
        finish()
    }
}
