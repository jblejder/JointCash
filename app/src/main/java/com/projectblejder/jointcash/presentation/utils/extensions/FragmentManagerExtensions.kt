package com.projectblejder.jointcash.presentation.utils.extensions

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

fun FragmentManager.inTransaction(transaction: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        transaction()
        commit()
    }
}

fun FragmentManager.inSyncTransaction(transaction: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        transaction()
        commitNow()
    }
}
