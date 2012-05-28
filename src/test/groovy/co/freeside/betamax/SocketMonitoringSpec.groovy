package co.freeside.betamax

import co.freeside.betamax.performance.SocketMonitoringSystem
import co.freeside.betamax.stats.StatsManager
import spock.lang.Specification
import co.freeside.betamax.logging.SocketLog

class SocketMonitoringSpec extends Specification {

    void setupSpec() {
		SocketMonitoringSystem.initForDelegator();
		SocketMonitoringSystem.getInstance().add(StatsManager.getSocketStats());
		SocketMonitoringSystem.getInstance().add(new SocketLog());
	}

    void 'can monitor a URLConnection'() {
        when:
        def connection = new URL('http://freeside.co/').openConnection()

        then:
        connection.inputStream.text =~ /^<!doctype html>/

		and:
		StatsManager.socketStats.bytesRead > 0
		StatsManager.socketStats.bytesWritten > 0
	}

    void 'can monitor a HTTPS URLConnection'() {
        when:
        def connection = new URL('https://raw.github.com/robfletcher/betamax/master/readme.md').openConnection()

        then:
        connection.inputStream.text =~ /^# Betamax/

		and:
		StatsManager.socketStats.bytesRead > 0
		StatsManager.socketStats.bytesWritten > 0
    }

}
