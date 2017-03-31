package com.github.ddmytrenko.unittestsdemo

import spock.lang.Specification

class DummySpec extends Specification {

    def "should add properly"() {
        given:
        def a = 4
        def b = 5

        when:
        def c = a + b

        then:
        c == 9
    }
}