package co.freeside.betamax.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import co.freeside.betamax.performance.*;
import co.freeside.betamax.performance.SocketMonitor;

import java.io.*;
import java.net.Socket;

import co.freeside.betamax.stats.StatsManager;

@Aspect
public class BoundSocket {
    public BoundSocket() {
        SocketMonitoringSystem.getInstance().add(StatsManager.getSocketStats());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                StatsManager.getSocketStats().printStats();
            }
        });
    }
    @Pointcut("call(* java.net.Socket.getInputStream()) && target(s)")
    void input(Socket s) {
    }

    @Around("input(s)")
    public Object wrapInputStream(ProceedingJoinPoint joinPoint, Socket s)
        throws Throwable {
        InputStream in = (InputStream) joinPoint.proceed();
        return new SocketMonitoringInputStream(s, in);
    }

    @Pointcut("call(* java.net.Socket.getOutputStream()) && target(s)")
    void output(Socket s) {
    }

    @Around("output(s)")
    public Object wrapOutputStream(ProceedingJoinPoint joinPoint, Socket s)
        throws Throwable {
        OutputStream out = (OutputStream) joinPoint.proceed();
        return new SocketMonitoringOutputStream(s, out);
    }
}