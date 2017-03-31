package com.github.ddmytrenko.unittestsdemo

import android.database.Cursor
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CursorInstrumentedTest {

    @Test
    fun shouldMockCursorCount() {

        // given
        val cursor = mock<Cursor> {
            on { count } doReturn 1
        }

        // when
        val count = cursor.count

        // than
        assert(count > 0)
    }
}