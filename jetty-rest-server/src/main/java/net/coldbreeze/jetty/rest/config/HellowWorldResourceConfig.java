package net.coldbreeze.jetty.rest.config;

import net.coldbreeze.jetty.rest.resource.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;

public class HellowWorldResourceConfig extends ResourceConfig {
    public HellowWorldResourceConfig() {
        packages(HelloWorldResource.class.getPackage().getName());
    }
}
