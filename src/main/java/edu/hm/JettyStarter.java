package edu.hm;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;

/**
 * Start the application without an AppServer like tomcat.
 * @author ab@cs.hm.edu
 *
 */
public final class JettyStarter {
    /**
     * Private default constructor.
     */
    public JettyStarter() { }

    public static final String APP_URL = "/";
    public static final int PORT = 8082;
    public static final String WEBAPP_DIR = "./src/main/webapp/";
    public static final Object MONITOR = new Object();

    /**
     * Main method to start jetty.
     * @param args optional args string parameter.
     * @throws Exception May throw exception
     */
    public static void main(String... args) throws Exception {
        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        synchronized (JettyStarter.MONITOR) {
            JettyStarter.MONITOR.wait();
        }
        System.out.println("Jetty stopped");
    }



}
