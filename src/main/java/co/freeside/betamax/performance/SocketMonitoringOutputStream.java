package co.freeside.betamax.performance;

import java.io.*;
import java.net.Socket;

public class SocketMonitoringOutputStream extends OutputStream {
	private final OutputStream out;
	private final Socket socket;

	public SocketMonitoringOutputStream(Socket socket, OutputStream out) throws IOException {
		this.out = out;
		this.socket = socket;
	}

	public void write(int b) throws IOException {
		System.out.print('>');
		out.write(b);
		SocketMonitoringSystem.getInstance().write(socket, b);
	}

	public void write(byte[] b, int off, int length) throws IOException {
		System.out.print('>');
		out.write(b, off, length);
		SocketMonitoringSystem.getInstance().write(socket, b, off, length);
	}
}