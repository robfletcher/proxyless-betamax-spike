package co.freeside.betamax.logging;

import java.io.*;
import java.net.*;
import co.freeside.betamax.performance.*;

public class SocketLog implements SocketMonitor {

	@Override
	public void write(Socket socket, int data) throws IOException {
		System.out.print((char)data);
	}

	@Override
	public void write(Socket socket, byte[] data, int off, int len) throws IOException {
		byte[] bytes = new byte[len];
		System.arraycopy(data, off, bytes, 0, len);
		System.out.print(new String(bytes));
	}

	@Override
	public void read(Socket socket, int data) throws IOException {
		System.out.print((char)data);
	}

	@Override
	public void read(Socket socket, byte[] data, int off, int len) throws IOException {
		byte[] bytes = new byte[len];
		System.arraycopy(data, off, bytes, 0, len);
		System.out.print(new String(bytes));
	}
}
