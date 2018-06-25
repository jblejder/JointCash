package com.projectblejder.jointcash.infrastructure.dao

import com.projectblejder.jointcash.infrastructure.BaseAppDatabaseTest
import com.projectblejder.jointcash.infrastructure.models.Group
import com.projectblejder.jointcash.infrastructure.models.Person
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PersonGroupTest : BaseAppDatabaseTest() {

    lateinit var groups: GroupsDao
    lateinit var persons: PersonsDao
    lateinit var personGroup: PersonGroupDao

    @Test
    fun `can add person to group`() {

        groups.create(Group(null, "name"))

        persons.create(Person(null, "first"))
        persons.create(Person(null, "second"))

        personGroup.addPersonToGroup(1, 1)
        personGroup.addPersonToGroup(2, 1)

        val group = personGroup.personsInGroup(1)


        val expected = listOf(
                Person(1, "first"),
                Person(2, "second")
        )

        Assert.assertEquals(expected, group)
    }

    @Test
    fun `can remove person from group`() {

        groups.create(Group(null, "name"))

        persons.create(Person(null, "first"))
        persons.create(Person(null, "second"))

        personGroup.addPersonToGroup(1, 1)
        personGroup.addPersonToGroup(2, 1)


        personGroup.removePersonFromGroup(1, 1)

        val group = personGroup.personsInGroup(1)


        val expected = listOf(
                Person(2, "second")
        )

        Assert.assertEquals(expected, group)
    }

    @Before
    fun before() {
        groups = db.groups()
        persons = db.persons()
        personGroup = db.personGroup()
    }
}
