package co.freeside.betamax.stats;

import co.freeside.betamax.performance.SocketMonitor;
import java.net.*;

public interface SocketStatsMBean {
    void printStats();
    void reset();
    long getBytesRead();
    long getBytesWritten();
    String getIndividualBytesWritten();
    String getIndividualBytesRead();
}