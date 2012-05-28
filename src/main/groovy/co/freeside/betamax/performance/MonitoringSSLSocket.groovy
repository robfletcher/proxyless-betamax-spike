package co.freeside.betamax.performance

import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSession
import javax.net.ssl.HandshakeCompletedListener

class MonitoringSSLSocket extends SSLSocket {

	private final SSLSocket delegate

	MonitoringSSLSocket(SSLSocket delegate) {
		this.delegate = delegate
	}

	@Override
	InputStream getInputStream() throws IOException {
		println 'hackin ur secure inputz'
		def real = delegate.getInputStream()
		new SocketMonitoringInputStream(delegate, real)
	}

	@Override
	OutputStream getOutputStream() throws IOException {
		println 'hackin ur secure outputz'
		def real = delegate.getOutputStream()
		new SocketMonitoringOutputStream(delegate, real)
	}

	@Override
	String[] getSupportedCipherSuites() {
		delegate.getSupportedCipherSuites()
	}

	@Override
	String[] getEnabledCipherSuites() {
		delegate.getEnabledCipherSuites()
	}

	@Override
	void setEnabledCipherSuites(String[] suites) {
		delegate.setEnabledCipherSuites()
	}

	@Override
	String[] getSupportedProtocols() {
		delegate.getSupportedProtocols()
	}

	@Override
	String[] getEnabledProtocols() {
		delegate.getEnabledProtocols()
	}

	@Override
	void setEnabledProtocols(String[] protocols) {
		delegate.setEnabledProtocols(protocols)
	}

	@Override
	SSLSession getSession() {
		delegate.getSession()
	}

	@Override
	void addHandshakeCompletedListener(HandshakeCompletedListener listener) {
		delegate.addHandshakeCompletedListener(listener)
	}

	@Override
	void removeHandshakeCompletedListener(HandshakeCompletedListener listener) {
		delegate.removeHandshakeCompletedListener(listener)
	}

	@Override
	void startHandshake() {
		delegate.startHandshake()
	}

	@Override
	void setUseClientMode(boolean mode) {
		delegate.setUseClientMode(mode)
	}

	@Override
	boolean getUseClientMode() {
		delegate.getUseClientMode()
	}

	@Override
	void setNeedClientAuth(boolean need) {
		delegate.setNeedClientAuth(need)
	}

	@Override
	boolean getNeedClientAuth() {
		delegate.getNeedClientAuth()
	}

	@Override
	void setWantClientAuth(boolean want) {
		delegate.setWantClientAuth(want)
	}

	@Override
	boolean getWantClientAuth() {
		delegate.getWantClientAuth()
	}

	@Override
	void setEnableSessionCreation(boolean flag) {
		delegate.setEnableSessionCreation(flag)
	}

	@Override
	boolean getEnableSessionCreation() {
		delegate.getEnableSessionCreation()
	}
}