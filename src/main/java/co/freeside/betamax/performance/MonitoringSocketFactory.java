package co.freeside.betamax.performance;

import java.net.*;
import javax.net.ssl.*;

public class MonitoringSocketFactory implements SocketImplFactory {
    public SocketImpl createSocketImpl() {
        try {
            return new MonitoringSocketImpl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}