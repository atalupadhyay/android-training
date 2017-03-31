package com.github.ddmytrenko.unittestsdemo

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

class DataClassMockingTest {

    @Test
    fun shouldMockDataClassProperly() {
        // when
        val person = mock<Person> {
            on { name } doReturn EXPECTED_NAME
            on { surname } doReturn EXPECTED_SURNAME
        }

        // then
        assert(person.name == EXPECTED_NAME)
        assert(person.surname == EXPECTED_SURNAME)
    }

    companion object {
        const val EXPECTED_NAME = "Дмитро"
        const val EXPECTED_SURNAME = "Дмитренко"
    }

    data class Person(val name: String, val surname: String)
}