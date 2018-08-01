package com.projectblejder.jointcash.presentation.groups

import com.projectblejder.jointcash.domain.models.Person
import com.projectblejder.jointcash.domain.models.PersonId
import com.projectblejder.jointcash.domain.readModels.PersonsReadModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CreateGroupViewModel @Inject constructor(
        val personsRM: PersonsReadModel
) {

    private val selected = PublishSubject.create<Set<PersonId>>()
    val selectedPersons = composeSelectedPersonsObservable()

    fun composeSelectedPersonsObservable(): Observable<List<SelectedPersonItemModel>> {
        var tmpPersons: List<Person> = emptyList()
        var tmpSelections: Set<PersonId> = emptySet()

        val persons = personsRM.observe().doOnNext { tmpPersons = it }.toObservable()
        val selections = selected.doOnNext { tmpSelections = it }

        return Observable.merge(
                persons.map { merge(tmpSelections, it) },
                selections.map { merge(it, tmpPersons) }
        )
    }

    fun merge(selected: Set<PersonId>, persons: List<Person>): List<SelectedPersonItemModel> {
        return persons.map { SelectedPersonItemModel(it, selected.contains(it.id)) }
    }

    fun updateSelected(item: SelectedPersonItemModel) {
        val set = HashSet(selected.value ?: emptySet())
        when (set.contains(item.person.id)) {
            true -> set.remove(item.person.id)
            false -> set.add(item.person.id)
        }
        selected.onNext(set)
    }


}
