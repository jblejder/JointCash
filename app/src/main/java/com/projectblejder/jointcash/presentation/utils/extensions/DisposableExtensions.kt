package com.projectblejder.jointcash.presentation.utils.extensions

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

fun Disposable.disposeWith(disposeBag: DisposableContainer) {
    disposeBag.add(this)
}
