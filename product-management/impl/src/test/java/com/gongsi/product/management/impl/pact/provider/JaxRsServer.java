package com.gongsi.product.management.impl.pact.provider;

import io.undertow.Undertow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.ws.rs.core.Application;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

public class JaxRsServer {
    private static final String HOST = "localhost";
    static final int PORT = 8112;
    private static final String CONTEXT_PATH = "/";

    private static final UndertowJaxrsServer server = new UndertowJaxrsServer();

    private static final AtomicBoolean started = new AtomicBoolean(false);
    private static final Map<Class, Object> deployedControllers = new ConcurrentHashMap<>();

    private JaxRsServer() {
    }

    public static UndertowJaxrsServer getServer() {
        return server;
    }

    public static void startServer() {
        // start precondition
        if (started.get()) {
            return;
        }

        started.set(true);

        final Undertow.Builder builder = Undertow.builder()
                .addHttpListener(PORT, HOST);
        server.start(builder);

    }

    public static void deploy(final Object... controllers) {
        deploy(CONTEXT_PATH, controllers);
    }

    public static void deploy(final String contextPath, final Object... controllers) {
        final Map<Class, Object> map = new HashMap<>();
        for (final Object controller : controllers) {
            map.put(controller.getClass(), controller);
        }

        final List<Object> newControllers = new ArrayList<>();
        for (final Map.Entry<Class, Object> entry : map.entrySet()) {
            if (!deployedControllers.containsKey(entry.getKey())) {
                deployedControllers.put(entry.getKey(), entry.getValue());
                newControllers.add(entry.getValue());
            }
        }

        if (newControllers.isEmpty()) {
            return;
        }

        server.deploy(new Application() {
            @Override
            public Set<Object> getSingletons() {
                return new HashSet<>(JaxRsServer.deployedControllers.values());
            }
        }, contextPath);
    }


    public static void stopServer() {
        // stop precondition
        if (!started.get()) {
            return;
        }
        server.stop();
        started.set(false);
    }
}