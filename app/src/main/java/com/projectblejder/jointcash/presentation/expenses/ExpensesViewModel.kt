package com.projectblejder.jointcash.presentation.expenses

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.projectblejder.jointcash.domain.readModels.GroupsReadModel
import io.reactivex.Completable
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ExpensesViewModel
@Inject constructor(
        val groupsReadModel: GroupsReadModel
) {

    val state = ViewState()


    fun load(): Completable {
        return groupsReadModel.selectedGroup()
                .doOnSuccess { state.title = it.name }
                .ignoreElement()
    }

    class ViewState : BaseObservable() {
        @get:Bindable
        var title: String by Bind("")
    }
}

class Bind<TRef : BaseObservable, TVal>(private var value: TVal) : ReadWriteProperty<TRef, TVal> {

    override fun getValue(thisRef: TRef, property: KProperty<*>): TVal {
        return value
    }

    override fun setValue(thisRef: TRef, property: KProperty<*>, value: TVal) {
        this.value = value
        val field = com.projectblejder.jointcash.BR::class.java.getField(property.name)
        thisRef.notifyPropertyChanged(field.getInt(null))
    }
}
