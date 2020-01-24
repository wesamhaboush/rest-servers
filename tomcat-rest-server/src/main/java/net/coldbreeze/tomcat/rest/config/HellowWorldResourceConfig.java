package net.coldbreeze.tomcat.rest.config;

import net.coldbreeze.tomcat.rest.resource.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;

public class HellowWorldResourceConfig extends ResourceConfig {
    public HellowWorldResourceConfig() {
        packages(HelloWorldResource.class.getPackage().getName());
    }
}
