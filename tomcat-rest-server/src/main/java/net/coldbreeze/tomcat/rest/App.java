package net.coldbreeze.tomcat.rest;

import net.coldbreeze.tomcat.rest.config.HellowWorldResourceConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws LifecycleException {
        String webappDirLocation = ".";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setBaseDir(".");
        tomcat.setPort(Integer.parseInt(webPort));
        Context context = tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        ServletContainer servlet = resourceConfig();
        Tomcat.addServlet(context,"jersey-container-servlet", servlet);
        context.addServletMappingDecoded("/*", "jersey-container-servlet");

        try {
            tomcat.getHost().setDeployOnStartup(true);
            tomcat.start();
            tomcat.getServer().await();
            ;
        } finally {
            tomcat.destroy();
        }
    }

    private static ServletContainer resourceConfig() {
        return new ServletContainer(new HellowWorldResourceConfig());
    }
}
