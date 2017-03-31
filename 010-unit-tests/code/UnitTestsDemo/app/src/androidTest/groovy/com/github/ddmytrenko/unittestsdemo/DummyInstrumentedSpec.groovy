package com.github.ddmytrenko.unittestsdemo

import android.widget.Button
import com.andrewreitz.spock.android.AndroidSpecification

class DummyInstrumentedSpec extends AndroidSpecification {

    def "should add properly on Android"() {
        given:
        def a = 4
        def b = 5

        when:
        def c = a + b

        then:
        c == 9
    }

    def "should mock Button on Android"() {
        given:
        def btn = Mock(Button)

        when:
        btn.performClick()

        then:
        1 * btn.performClick()
    }
}