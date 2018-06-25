package com.projectblejder.jointcash

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor

class TestSchedulerRule : TestRule {

    private val immediate = object : Scheduler() {
        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
        }
    }
    val testScheduler = TestScheduler()

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {

                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate };
                RxJavaPlugins.setIoSchedulerHandler { scheduler -> testScheduler }
                RxJavaPlugins.setComputationSchedulerHandler { scheduler -> testScheduler }
                RxJavaPlugins.setNewThreadSchedulerHandler { scheduler -> testScheduler }
                RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler -> immediate }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}
