package co.freeside.betamax.performance

import javax.net.ssl.*

class MonitoringSSLSocketFactory extends SSLSocketFactory {

	@Delegate private SSLSocketFactory delegate

	MonitoringSSLSocketFactory(SSLSocketFactory delegate) {
		this.delegate = delegate
	}

	@Override
	Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
		def actualSocket = (SSLSocket) delegate.createSocket(socket, host, port, autoClose)
		new MonitoringSSLSocket(actualSocket)
	}

}
