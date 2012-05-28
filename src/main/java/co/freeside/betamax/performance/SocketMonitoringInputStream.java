package co.freeside.betamax.performance;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class SocketMonitoringInputStream extends InputStream {
    private final Socket socket;
    private final InputStream in;

    public SocketMonitoringInputStream(Socket socket, InputStream in) throws IOException {
        this.socket = socket;
        this.in = in;
    }

    public int read() throws IOException {
		System.out.print('<');
        int result = in.read();
        if (result != -1) {
            SocketMonitoringSystem.getInstance().read(socket, result);
        }
        return result;
    }

    public int read(byte[] b, int off, int len) throws IOException {
		System.out.print('<');
		int length = in.read(b, off, len);
        if (length != -1) {
			SocketMonitoringSystem.getInstance().read(socket, b, off, length);
        }
        return length;
    }
}