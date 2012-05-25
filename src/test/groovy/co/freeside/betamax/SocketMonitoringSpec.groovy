package co.freeside.betamax

import spock.lang.Specification
import co.freeside.betamax.performance.SocketMonitoringSystem

class SocketMonitoringSpec extends Specification {

    void setupSpec() {
        SocketMonitoringSystem.initForDelegator();
    }

    void 'can monitor a URLConnection'() {
        when:
        def connection = new URL('http://freeside.co/').openConnection()

        then:
        connection.inputStream.text =~ /^<!doctype html>/
    }

}
