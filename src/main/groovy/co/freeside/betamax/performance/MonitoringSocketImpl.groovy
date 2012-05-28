package co.freeside.betamax.performance;

import java.lang.reflect.Field

class MonitoringSocketImpl extends SocketImpl {

	private final SocketImpl delegate

	MonitoringSocketImpl() {
		def delegateType = Class.forName('java.net.SocksSocketImpl')
		def delegateConstructor = delegateType.getDeclaredConstructor()
		delegateConstructor.accessible = true
		delegate = delegateConstructor.newInstance() as SocketImpl
	}

//	private Socket getSocket() throws IOException {
//		try {
//			Field socket = SocketImpl.class.getDeclaredField("socket")
//			socket.accessible = true
//			socket.get(this) as Socket
//		} catch (Exception e) {
//			throw new IOException("Could not discover real socket")
//		}
//	}

	@Override
	InputStream getInputStream() throws IOException {
		println 'hackin ur inputz'
		def real = delegate.getInputStream()
		new SocketMonitoringInputStream(getSocket(), real)
	}

	@Override
	OutputStream getOutputStream() throws IOException {
		println 'hackin ur outputz'
		def real = delegate.getOutputStream()
		new SocketMonitoringOutputStream(getSocket(), real)
	}

	@Override
	protected void create(boolean stream) {
		delegate.create(stream)
	}

	@Override
	protected void connect(String host, int port) {
		delegate.connect(host, port)
	}

	@Override
	protected void connect(InetAddress address, int port) {
		delegate.connect(address, port)
	}

	@Override
	protected void connect(SocketAddress address, int timeout) {
		delegate.connect(address, timeout)
	}

	@Override
	protected void bind(InetAddress host, int port) {
		delegate.bind(host, port)
	}

	@Override
	protected void listen(int backlog) {
		delegate.listen(backlog)
	}

	@Override
	protected void accept(SocketImpl s) {
		delegate.accept(s)
	}

	@Override
	protected int available() {
		delegate.available()
	}

	@Override
	protected void close() {
		delegate.close()
	}

	@Override
	protected void sendUrgentData(int data) {
		delegate.sendUrgentData(data)
	}

	@Override
	void setOption(int optID, Object value) {
		delegate.setOption(optID, value)
	}

	@Override
	Object getOption(int optID) {
		delegate.getOption(optID)
	}
}